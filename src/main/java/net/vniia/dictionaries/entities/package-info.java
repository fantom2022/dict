@GenericGenerator(
        name = "typed-sequence",
        strategy = "net.vniia.common.jpa.identity.TypedSequenceGenerator",
        parameters = {
                @Parameter(name="prefer_sequence_per_entity", value="true"),
                @Parameter(name="increment_size", value="50")
        })
package net.vniia.dictionaries.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
