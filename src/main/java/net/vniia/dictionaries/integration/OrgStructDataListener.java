package net.vniia.dictionaries.integration;

import net.vniia.dictionaries.entities.*;
import net.vniia.dictionaries.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
public class OrgStructDataListener extends TableDataListener {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentChiefRepository departmentChiefRepository;

    @KafkaListener(topics = "org-struct-places", containerFactory = "dictionaryPlaces",
            id = "dictionaryPlaces", idIsGroup = false)
    @Transactional
    public void onPlaceMessage(List<Place> placeList) {
        saveAll(placeRepository, placeList, Place::getId);
    }

    @KafkaListener(topics = "org-struct-departments", containerFactory = "dictionaryDepartments",
            id = "dictionaryDepartments", idIsGroup = false)
    @Transactional
    public void onDepartmentMessage(List<Department> departmentList) {
        saveAll(departmentRepository, departmentList, Department::getId);
    }

    @KafkaListener(topics = "org-struct-department-chief", containerFactory = "dictionaryDepartmentChiefs",
            id = "dictionaryDepartmentChiefs", idIsGroup = false)
    @Transactional
    public void onDepartmentChiefMessage(List<DepartmentChief[]> departmentChiefs) {
        List<DepartmentChief> departmentChiefsList = Arrays.asList(departmentChiefs.get(departmentChiefs.size() - 1));
        List<DepartmentChief> departmentChiefsDelList = departmentChiefRepository.findAll()
                .stream()
                .filter(oldChief -> departmentChiefsList.stream()
                        .noneMatch(newChief -> newChief.isChief() == oldChief.isChief() &&
                                newChief.getDepartmentId().equals(oldChief.getDepartmentId()) &&
                                newChief.getPersonalId().equals(oldChief.getPersonalId())
                        ))
                .collect(Collectors.toList());
        if (!departmentChiefsDelList.isEmpty())
            departmentChiefRepository.deleteAll(departmentChiefsDelList);
        
        departmentChiefRepository.saveAll(departmentChiefsList);
    }

    @KafkaListener(topics = "org-struct-person", containerFactory = "dictionaryPerson",
            id = "dictionaryPerson", idIsGroup = false)
    @Transactional
    public void onPersonMessage(List<Person> personList) {
        saveAll(personRepository, personList, Person::getId);
    }

    @KafkaListener(topics = "org-struct-personal", containerFactory = "dictionaryPersonal",
            id = "dictionaryPersonal", idIsGroup = false)
    @Transactional
    public void onPersonalMessage(List<Personal> personalList) {
        saveAll(personalRepository, personalList, Personal::getId);
    }

    @KafkaListener(topics = "org-struct-appointments", containerFactory = "dictionaryAppointments",
            id = "dictionaryAppointments", idIsGroup = false)
    @Transactional
    public void onAppointmentMessage(List<Appointment> appointmentList) {
        saveAll(appointmentRepository,
                appointmentList.stream().filter(a -> !a.isArchive()).collect(Collectors.toList()),
                Appointment::getId);
        appointmentList.stream().filter(Appointment::isArchive).forEach(a -> {
            appointmentRepository.findById(a.getId()).ifPresent(a2 -> appointmentRepository.delete(a2));
        });
    }

    @KafkaListener(topics = "org-struct-position", containerFactory = "dictionaryPositions",
            id = "dictionaryPositions", idIsGroup = false)
    @Transactional
    public void onPositionMessage(List<Position> positionList) {
        saveAll(positionRepository, positionList, Position::getId);
    }

    @Override
    protected void resetData(String topicName) {
        switch (topicName) {
            case "org-struct-places":
                placeRepository.deleteAllInBatch();
                break;
            case "org-struct-departments":
                departmentRepository.deleteAllInBatch();
                break;
            case "org-struct-person":
                personRepository.deleteAllInBatch();
                break;
            case "org-struct-personal":
                personalRepository.deleteAllInBatch();
                break;
            case "org-struct-appointments":
                appointmentRepository.deleteAllInBatch();
                break;
            case "org-struct-position":
                positionRepository.deleteAllInBatch();
                break;
            case "org-struct-department-chief":
                departmentChiefRepository.deleteAllInBatch();
                break;
        }
    }
}
