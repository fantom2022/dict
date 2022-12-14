/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.vniia.dictionaries.integration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.core.ResolvableType;
import org.springframework.kafka.support.mapping.AbstractJavaTypeMapper;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Map;

/**
 * Generic {@link Deserializer} for receiving JSON from Kafka and return Java objects.
 *
 * @param <T> class of the entity, representing messages
 *
 * @author Igor Stepanov
 * @author Artem Bilan
 * @author Gary Russell
 * @author Yanming Zhou
 * @author Elliot Kennedy
 */
public class JsonDeserializer2<T> implements Deserializer<T> {

	/**
	 * Kafka config property for the default key type if no header.
	 * @deprecated in favor of {@link #KEY_DEFAULT_TYPE}
	 */
	@Deprecated
	public static final String DEFAULT_KEY_TYPE = "spring.json.key.default.type";

	/**
	 * Kafka config property for the default value type if no header.
	 * @deprecated in favor of {@link #VALUE_DEFAULT_TYPE}
	 */
	@Deprecated
	public static final String DEFAULT_VALUE_TYPE = "spring.json.default.value.type";

	/**
	 * Kafka config property for the default key type if no header.
	 */
	public static final String KEY_DEFAULT_TYPE = "spring.json.key.default.type";

	/**
	 * Kafka config property for the default value type if no header.
	 */
	public static final String VALUE_DEFAULT_TYPE = "spring.json.value.default.type";

	/**
	 * Kafka config property for trusted deserialization packages.
	 */
	public static final String TRUSTED_PACKAGES = "spring.json.trusted.packages";

	protected final ObjectMapper objectMapper;

	protected Class<T> targetType;

	private volatile ObjectReader reader;

	protected Jackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();

	private boolean typeMapperExplicitlySet = false;

	public JsonDeserializer2() {
		this((Class<T>) null);
	}

	protected JsonDeserializer2(ObjectMapper objectMapper) {
		this(null, objectMapper);
	}

	public JsonDeserializer2(Class<T> targetType) {
		this(targetType, new ObjectMapper());
		this.objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {
			@Override
			public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
				if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
					return Instant.ofEpochMilli(jsonParser.getLongValue()).atZone(ZoneOffset.UTC).toLocalDate();
				}
				return null;
			}
		});
		javaTimeModule.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
			@Override
			public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
				jsonGenerator.writeNumber(localDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
			}
		});
		this.objectMapper.registerModule(javaTimeModule);
	}

	@SuppressWarnings("unchecked")
	public JsonDeserializer2(Class<T> targetType, ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "'objectMapper' must not be null.");
		this.objectMapper = objectMapper;
		if (targetType == null) {
			targetType = (Class<T>) ResolvableType.forClass(getClass()).getSuperType().resolveGeneric(0);
		}
		this.targetType = targetType;
		addTargetPackageToTrusted();
	}

	public Jackson2JavaTypeMapper getTypeMapper() {
		return this.typeMapper;
	}

	/**
	 * Set a customized type mapper.
	 * @param typeMapper the type mapper.
	 * @since 2.1
	 */
	public void setTypeMapper(Jackson2JavaTypeMapper typeMapper) {
		Assert.notNull(typeMapper, "'typeMapper' cannot be null");
		this.typeMapper = typeMapper;
		this.typeMapperExplicitlySet = true;
	}

	/**
	 * Configure the default Jackson2JavaTypeMapper to use key type headers.
	 * @param isKey Use key type headers if true
	 * @since 2.1.3
	 */
	public void setUseTypeMapperForKey(boolean isKey) {
		if (!this.typeMapperExplicitlySet) {
			if (this.getTypeMapper() instanceof AbstractJavaTypeMapper) {
				AbstractJavaTypeMapper typeMapper = (AbstractJavaTypeMapper) this.getTypeMapper();
				typeMapper.setUseForKey(isKey);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		setUseTypeMapperForKey(isKey);
		try {
			if (isKey && configs.containsKey(KEY_DEFAULT_TYPE)) {
				if (configs.get(KEY_DEFAULT_TYPE) instanceof Class) {
					this.targetType = (Class<T>) configs.get(KEY_DEFAULT_TYPE);
				}
				else if (configs.get(KEY_DEFAULT_TYPE) instanceof String) {
					this.targetType = (Class<T>) ClassUtils.forName((String) configs.get(KEY_DEFAULT_TYPE), null);
				}
				else {
					throw new IllegalStateException(KEY_DEFAULT_TYPE + " must be Class or String");
				}
			}
			// TODO don't forget to remove these code after DEFAULT_VALUE_TYPE being removed.
			else if (!isKey && configs.containsKey("spring.json.default.value.type")) {
				if (configs.get("spring.json.default.value.type") instanceof Class) {
					this.targetType = (Class<T>) configs.get("spring.json.default.value.type");
				}
				else if (configs.get("spring.json.default.value.type") instanceof String) {
					this.targetType = (Class<T>) ClassUtils
						.forName((String) configs.get("spring.json.default.value.type"), null);
				}
				else {
					throw new IllegalStateException("spring.json.default.value.type must be Class or String");
				}
			}
			else if (!isKey && configs.containsKey(VALUE_DEFAULT_TYPE)) {
				if (configs.get(VALUE_DEFAULT_TYPE) instanceof Class) {
					this.targetType = (Class<T>) configs.get(VALUE_DEFAULT_TYPE);
				}
				else if (configs.get(VALUE_DEFAULT_TYPE) instanceof String) {
					this.targetType = (Class<T>) ClassUtils.forName((String) configs.get(VALUE_DEFAULT_TYPE), null);
				}
				else {
					throw new IllegalStateException(VALUE_DEFAULT_TYPE + " must be Class or String");
				}
			}
			addTargetPackageToTrusted();
		}
		catch (ClassNotFoundException | LinkageError e) {
			throw new IllegalStateException(e);
		}
		if (configs.containsKey(TRUSTED_PACKAGES)) {
			if (configs.get(TRUSTED_PACKAGES) instanceof String) {
				this.typeMapper.addTrustedPackages(
						StringUtils.commaDelimitedListToStringArray((String) configs.get(TRUSTED_PACKAGES)));
			}
		}
	}

	/**
	 * Add trusted packages for deserialization.
	 * @param packages the packages.
	 * @since 2.1
	 */
	public void addTrustedPackages(String... packages) {
		this.typeMapper.addTrustedPackages(packages);
	}

	private void addTargetPackageToTrusted() {
		if (this.targetType != null) {
			addTrustedPackages(ClassUtils.getPackageName(this.targetType).replaceFirst("\\[L", ""));
		}
	}

	@Override
	public T deserialize(String topic, Headers headers, byte[] data) {
		if (data == null) {
			return null;
		}
		JavaType javaType = this.typeMapper.toJavaType(headers);
		if (javaType == null) {
			Assert.state(this.targetType != null, "No type information in headers and no default type provided");
			return deserialize(topic, data);
		}
		else {
			try {
				return this.objectMapper.readerFor(javaType).readValue(data);
			}
			catch (IOException e) {
				throw new SerializationException("Can't deserialize data [" + Arrays.toString(data) +
						"] from topic [" + topic + "]", e);
			}
		}
	}

	@Override
	public T deserialize(String topic, byte[] data) {
		if (data == null) {
			return null;
		}
		if (this.reader == null) {
			this.reader = this.objectMapper.readerFor(this.targetType);
		}
		try {
			T result = null;
			if (data != null) {
				result = this.reader.readValue(data);
			}
			return result;
		}
		catch (IOException e) {
			throw new SerializationException("Can't deserialize data [" + Arrays.toString(data) +
					"] from topic [" + topic + "]", e);
		}
	}

	@Override
	public void close() {
		// No-op
	}
}
