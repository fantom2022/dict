package net.vniia.dictionaries.controllers;

import net.vniia.common.exception.NotFoundException;
import net.vniia.common.reader.PageQuery;
import net.vniia.common.reader.PageResponse;
import net.vniia.dictionaries.dto.DictionaryColumnDto;
import net.vniia.dictionaries.dto.DictionaryDto;
import net.vniia.dictionaries.dto.WasteDto;
import net.vniia.dictionaries.readers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("dictionaries")
@CrossOrigin("*")
public class DictionaryController {
    @Autowired
    private OrgStructReader orgStructReader;

    @Autowired
    private ErpReader erpReader;

    @Autowired
    private SkyReader skyReader;

    @Autowired
    private GeneralReader generalReader;

    @Autowired
    private WorkshopReader workshopReader;

    @Autowired
    private CodeDirectionReader codeDirectionReader;

    @PostMapping("{key}/items/search")
    private PageResponse<?> getItems(@PathVariable("key") String key, @RequestBody PageQuery pageQuery) {
        switch (key) {
            case "place":
                return orgStructReader.getPlaces(pageQuery);
            case "department":
                return orgStructReader.getDepartments(pageQuery);
            case "personal":
                return orgStructReader.getPersonal(pageQuery);
            case "personal-appointment":
                return orgStructReader.getPersonalAppointment(pageQuery);
            case "person":
                return orgStructReader.getPerson(pageQuery);
            case "personView":
                return orgStructReader.getPersonView(pageQuery);
            case "unitMeasure":
                return erpReader.getUnitMeasure(pageQuery);
            case "customOrder":
                return erpReader.getCustomOrder(pageQuery);
            case "contract":
                return erpReader.getContract(pageQuery);
            case "taxType":
                return erpReader.getTaxType(pageQuery);
            case "documentOperation":
                return skyReader.getDocumentOperation(pageQuery);
            case "materialResource":
                return generalReader.getMaterialResources(pageQuery);
            case "reference":
                return generalReader.getReferences(pageQuery);
            case "acceptanceType":
                return generalReader.getAcceptanceTypes(pageQuery);
            case "contractor":
                return skyReader.getContractor(pageQuery);
            case "planTask":
                return skyReader.getPlanTask(pageQuery);
            case "designation":
                return skyReader.getDesignations(pageQuery);
            case "productionPlanPart":
                return generalReader.getProductionPlanParts(pageQuery);
            case "productionDirective":
                return generalReader.getProductionDirectives(pageQuery);
            case "enlargedComposition":
                return skyReader.getEnlargedCompositions(pageQuery);
            case "storehouse":
                return generalReader.searchActualStorehouses(pageQuery);
            case "calendar":
                return skyReader.getCalendar(pageQuery);
            case "equipmentExemplars":
                return skyReader.getEquipmentExemplars(pageQuery);
            case "workshop":
                return workshopReader.getWorkshops(pageQuery);
            case "codeDirection":
                return codeDirectionReader.getCodeDirection(pageQuery);
            case "product":
                return skyReader.getProduct(pageQuery);
            case "storage":
                return generalReader.searchActualStorages(pageQuery);
            case "storageType":
                return generalReader.searchActualStorageTypes(pageQuery);
            case "waste":
                return skyReader.getWaste(pageQuery);
        }
        throw new NotFoundException("Справочник " + key + " не найден");
    }

    @GetMapping("views/{key}/{id}")
    private Object getViewValue(@PathVariable("key") String key, @PathVariable("id") Long id) {
        switch (key) {
            case "personView":
                return orgStructReader.getPersonViewById(id);
        }
        throw new NotFoundException("Представление " + key + " не найдено");
    }

    @GetMapping("{key}/metainf")
    private DictionaryDto getMetaInf(@PathVariable("key") String key) {
        switch (key) {
            case "place":
                return new DictionaryDto("Справочник организаций",
                        Arrays.asList(
                                new DictionaryColumnDto("name", "Код"),
                                new DictionaryColumnDto("fullName", "Наименование")
                        ));
            case "department":
                return new DictionaryDto("Справочник подразделений",
                        Arrays.asList(
                                new DictionaryColumnDto("code", "Код"),
                                new DictionaryColumnDto("name", "Наименование")
                        ));
            case "personal":
                return new DictionaryDto(
                        Arrays.asList(
                                new DictionaryColumnDto("personalNumber", "Таб. номер")
                        ));
            case "personal-appointment":
                return new DictionaryDto("Справочник сотрудников",
                        Arrays.asList(
                                new DictionaryColumnDto("personalNumber", "Таб. номер"),
                                new DictionaryColumnDto("person.lastName", "Фамилия"),
                                new DictionaryColumnDto("person.firstName", "Имя"),
                                new DictionaryColumnDto("person.middleName", "Отчество"),
                                new DictionaryColumnDto("position.name", "Должность"),
                                new DictionaryColumnDto("department.code", "Подразделение")
                        ));
            case "person":
                return new DictionaryDto("Справочник физических лиц",
                        Arrays.asList(
                                new DictionaryColumnDto("lastName", "Фамилия"),
                                new DictionaryColumnDto("firstName", "Имя"),
                                new DictionaryColumnDto("middleName", "Отчество")
                        ));
            case "unitMeasure":
                return new DictionaryDto("Справочник единиц измерения",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("fullName", "Полное наименование"),
                                new DictionaryColumnDto("name", "Обозначение"),
                                new DictionaryColumnDto("okeiCode", "Код ОКЕИ")

                        ));
            case "customOrder":
                return new DictionaryDto("Справочник заказов",
                        Arrays.asList(
                                new DictionaryColumnDto("orderNumber", "Номер заказа"),
                                new DictionaryColumnDto("openDate", "Дата открытия", "date"),
                                new DictionaryColumnDto("closeDate", "Дата закрытия", "date"),
                                new DictionaryColumnDto("stopDate", "Дата приостановки", "date"),
                                new DictionaryColumnDto("departments", "Подразделения"),
                                new DictionaryColumnDto("name", "Перечень работ (услуг)")
                        ));
            case "documentOperation":
                return new DictionaryDto("Справочник операций",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("name", "Обозначение операции")
                        ));
            case "materialResource":
                return new DictionaryDto("Справочник материальных ресурсов",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("designation", "Обозначение"),
                                new DictionaryColumnDto("name", "Наименование"),
                                new DictionaryColumnDto("reference.fullName", "Наименование справочника")
                        ));
            case "reference":
                return new DictionaryDto("Список справочников",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                //new DictionaryColumnDto("code", "Код"),
                                new DictionaryColumnDto("shortName", "Краткое наименование"),
                                new DictionaryColumnDto("fullName", "Полное наименование")
                        ));
            case "acceptanceType":
                return new DictionaryDto("Справочник видов приемки",
                        Arrays.asList(
                                new DictionaryColumnDto("code", "Вид приемки"),
                                new DictionaryColumnDto("name", "Расшифровка"),
                                new DictionaryColumnDto("importance", "Значимость")
                        ));
            case "contract":
                return new DictionaryDto("Справочник договоров",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                // new DictionaryColumnDto("parentId", "Ид родительского договора"),
                                new DictionaryColumnDto("registrationNumber", "Регистрационный номер"),
                                new DictionaryColumnDto("name", "Наименование"),
                                new DictionaryColumnDto("number", "Номер"),
                                new DictionaryColumnDto("date", "Дата заключения", "date"),
                                // new DictionaryColumnDto("directionId", "Ид типа договора"),
                                new DictionaryColumnDto("direction", "Тип договора"),
                                // new DictionaryColumnDto("contractorId", "Ид контрагента"),
                                // new DictionaryColumnDto("workKindId", "Ид типа работ по договору"),
                                new DictionaryColumnDto("workKind", "Тип работ по договору"),
                                // new DictionaryColumnDto("taxTypeId", "Ид ставки НДС"),
                                // new DictionaryColumnDto("customOrderId", "Ид заказа"),
                                new DictionaryColumnDto("isArchived", "Архивный")
                        ));
            case "taxType":
                return new DictionaryDto("Справочник НДС",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("name", "Налог"),
                                new DictionaryColumnDto("factor", "Множитель"),
                                new DictionaryColumnDto("isArchived", "Архивный")
                        ));
            case "contractor":
                return new DictionaryDto("Справочник контрагентов",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("parentId", "Ид родительской записи"),
                                // new DictionaryColumnDto("countryId", "Ид страны"),
                                new DictionaryColumnDto("countryCode", "Код страны"),
                                new DictionaryColumnDto("countryName", "Страна"),
                                new DictionaryColumnDto("type", "Тип"),
                                new DictionaryColumnDto("isClient", "Заказчик"),
                                new DictionaryColumnDto("isSupplier", "Поставщик"),
                                new DictionaryColumnDto("isCargoShipper", "Грузоотправитель"),
                                new DictionaryColumnDto("isCargoReceiver", "Грузополучатель"),
                                // new DictionaryColumnDto("ownerTypeId", "Ид организационно-правовой формы"),
                                new DictionaryColumnDto("ownerTypeName", "Организационно-правовая форма"),
                                new DictionaryColumnDto("name", "Наименование"),
                                new DictionaryColumnDto("fullName", "Полное наименование"),
                                new DictionaryColumnDto("localName", "Локализованное наименование"),
                                new DictionaryColumnDto("inn", "ИНН"),
                                new DictionaryColumnDto("kpp", "КПП"),
                                new DictionaryColumnDto("legalAddress", "Юридический адрес"),
                                new DictionaryColumnDto("actualAddress", "Фактический адрес"),
                                new DictionaryColumnDto("createDate", "Дата ввода", "date"),
                                new DictionaryColumnDto("archiveDate", "Дата перевода в архив", "date"),
                                new DictionaryColumnDto("isArchived", "Архивный")
                        ));
            case "planTask":
                return new DictionaryDto("Справочник позиций плана производства",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("category", "Категория"),
                                // new DictionaryColumnDto("parentId", "Ид родительской позиции"),
                                new DictionaryColumnDto("quantity", "Количество"),
                                new DictionaryColumnDto("launchDate", "Дата запуска", "date"),
                                new DictionaryColumnDto("releaseDate", "Дата выпуска", "date"),
                                new DictionaryColumnDto("note", "Примечание"),
                                new DictionaryColumnDto("closingDate", "Дата закрытия", "date"),
                                new DictionaryColumnDto("isClosed", "Закрытое"),
                                new DictionaryColumnDto("customer", "Заказчик"),
                                // new DictionaryColumnDto("priorityId", "Ид приоритета"),
                                new DictionaryColumnDto("priority", "Важность"),
                                // new DictionaryColumnDto("plantaskTypeId", "Ид типа"),
                                new DictionaryColumnDto("plantaskType", "Тип позиции"),
                                new DictionaryColumnDto("positionNumber", "Номер позиции"),
                                new DictionaryColumnDto("parentPositionNumber", "Номер родительской позиции"),
                                // new DictionaryColumnDto("plantaskKindId", "Ид вида"),
                                new DictionaryColumnDto("plantaskKind", "Вид позиции"),
                                new DictionaryColumnDto("plantaskStatusId", "Ид статуса"),
                                new DictionaryColumnDto("plantaskStatus", "Статус позиции"),
                                // new DictionaryColumnDto("customOrderId", "Ид заказа"),
                                new DictionaryColumnDto("customOrderNumber", "Заказ"),
                                // new DictionaryColumnDto("deliveryDepartmentId", "Ид подразделения - места сдачи"),
                                new DictionaryColumnDto("deliveryDepartment", "Подразделение - место сдачи"),
                                new DictionaryColumnDto("designation", "Обозначение"),
                                new DictionaryColumnDto("litera", "Литера"),
                                new DictionaryColumnDto("designationAnalog", "Обозначение аналога")
                        ));
            case "designation":
                return new DictionaryDto("Справочник обозначений",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("designation", "Обозначение"),
                                new DictionaryColumnDto("name", "Наименование"),
                                new DictionaryColumnDto("updateDate", "Дата обновление"),
                                new DictionaryColumnDto("updateUser", "Пользователь")
                        ));
            case "productionPlanPart":
                return new DictionaryDto("Справочник частей",
                        Arrays.asList(
                                new DictionaryColumnDto("part", "Номер"),
                                new DictionaryColumnDto("partName", "Наименование")
                        ));
            case "productionDirective":
                return new DictionaryDto("Справочник указаний производству",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("name", "Наименование"),
                                new DictionaryColumnDto("shortName", "Сокращение")
                        ));
            case "enlargedComposition":
                return new DictionaryDto("Справочник укрупнённых составов",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("designation", "Обозначение"),
                                new DictionaryColumnDto("vshi", "ВШИ")
                        ));
            case "storehouse":
                return new DictionaryDto("Справочник складов",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("name", "Полное наименование"),
                                new DictionaryColumnDto("code", "Код")
                        ));
            case "calendar":
                return new DictionaryDto("Календарь",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("updateDate", "Дата обновления"),
                                new DictionaryColumnDto("date", "Дата"),
                                new DictionaryColumnDto("type", "Тип")
                        ));
            case "equipmentExemplars":
                return new DictionaryDto("Справочник оборудования",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("designation", "Обозначение"),
                                new DictionaryColumnDto("name", "Наименование"),
                                new DictionaryColumnDto("inventoryNumber", "Инвентарный №"),
                                new DictionaryColumnDto("placeName", "Площадка"),
                                new DictionaryColumnDto("departmentCode", "Подразделение"),
                                new DictionaryColumnDto("buildingNumber", "Корпус"),
                                new DictionaryColumnDto("roomNumber", "Комната"),
                                new DictionaryColumnDto("notes", "Примечание"),
                                new DictionaryColumnDto("nameArea", "Участок")
                        ));
            case "workshop":
                return new DictionaryDto("Цеха/подразделения",
                        Arrays.asList(
                                new DictionaryColumnDto("code", "Код")
                        ));
            case "product":
                return new DictionaryDto("Справочник изделий",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("designation", "Обозначение"),
                                new DictionaryColumnDto("name", "Наименование"),
                                new DictionaryColumnDto("inventoryNumberTechdocs", "Инвентарный номер КД")
                        ));
            case "storage":
                return new DictionaryDto("Справочник мест хранения",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("name", "Наименование"),
                                new DictionaryColumnDto("shortName", "Краткое наименование")
                        ));
            case "storageType":
                return new DictionaryDto("Справочник типов мест хранения",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("name", "Наименование"),
                                new DictionaryColumnDto("shortName", "Краткое наименование")
                        ));
            case "waste":
                return new DictionaryDto("Справочник техотхода",
                        Arrays.asList(
                                new DictionaryColumnDto("id", "Идентификатор"),
                                new DictionaryColumnDto("productId", "Идентификатор изделия"),
                                new DictionaryColumnDto("waste", "Техотход (%)"),
                                new DictionaryColumnDto("witnessCount", "Количество свидетелей"),
                                new DictionaryColumnDto("destroyControlCount", "Количество на разрушающий контроль"),
                                new DictionaryColumnDto("setUpEquipmentCount", "Количество на наладку оборудования"),
                                new DictionaryColumnDto("optimalProductionBatch", "Оптимальная партия изготовления"),
                                new DictionaryColumnDto("updateDate", "Дата изменения")
                        ));
        }
        throw new NotFoundException("Справочник " + key + " не найден");
    }
}
