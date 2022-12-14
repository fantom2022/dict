package net.vniia.dictionaries.utils;

import net.vniia.common.exception.BusinessLogicException;
import net.vniia.common.mail.MailRecipient;
import net.vniia.dictionaries.dto.PersonDto;
import net.vniia.dictionaries.readers.OrgStructReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailHelper {

    private static OrgStructReader reader;

    @Autowired
    public MailHelper(OrgStructReader reader) {
        MailHelper.reader = reader;
    }

    public static MailRecipient getRecipient(long personId) {
        PersonDto person = reader.getPerson(personId);
        if (person == null)
            throw new BusinessLogicException(String.format("Физ. лицо с id =  не найдено!", personId));

        return new MailRecipient(String.valueOf(personId),
                    person.getFirstName(), person.getLastName());
    }
}
