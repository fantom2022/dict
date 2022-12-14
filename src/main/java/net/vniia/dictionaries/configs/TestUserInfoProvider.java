package net.vniia.dictionaries.configs;

import net.vniia.common.user.UserEmploymentDto;
import net.vniia.dictionaries.readers.OrgStructReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestUserInfoProvider implements net.vniia.common.user.UserInfoProvider {
    @Autowired
    OrgStructReader orgStructReader;

    @Override
    public UserEmploymentDto getUserEmployment(long personId, String personalNumber) {
        return orgStructReader.getUserEmployment(1L, "111");
    }

    @Override
    public List<UserEmploymentDto> getUserEmployments(long personId) {
        return orgStructReader.getUserEmployments(1L);
    }
}
