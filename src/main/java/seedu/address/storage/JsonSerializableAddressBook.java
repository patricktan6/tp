package seedu.address.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FitNus;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.person.Body;
import seedu.address.model.person.DailyCalorie;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.Routine;
import seedu.address.model.person.Slot;

/**
 * An Immutable FitNus that is serializable to JSON format.
 */
@JsonRootName(value = "fitnus")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    private static final String MESSAGE_DUPLICATE_ROUTINE = "Routines list contains duplicate routine(s).";
    private static final String MESSAGE_DUPLICATE_SLOT = "Slot list contains duplicate slot(s).";
    private static final String MESSAGE_DUPLICATE_DAILYCALORIE = "Calorie log contains duplicate calorie log(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedExercise> exercises = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();
    private final List<JsonAdaptedRoutine> routines = new ArrayList<>();
    private final List<JsonAdaptedSlot> slots = new ArrayList<>();
    private final List<JsonAdaptedDailyCalorie> dailyCalories = new ArrayList<>();
    private final JsonAdaptedBody body;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("exercises") List<JsonAdaptedExercise> exercises,
                                       @JsonProperty("lessons") List<JsonAdaptedLesson> lessons,
                                       @JsonProperty("routines") List<JsonAdaptedRoutine> routines,
                                       @JsonProperty("slots") List<JsonAdaptedSlot> slots,
                                       @JsonProperty("dailyCalories") List<JsonAdaptedDailyCalorie> dailyCalories,
                                       @JsonProperty("body") JsonAdaptedBody body) {
        this.persons.addAll(persons);
        this.exercises.addAll(exercises);
        this.lessons.addAll(lessons);
        this.routines.addAll(routines);
        this.slots.addAll(slots);
        this.dailyCalories.addAll(dailyCalories);
        this.body = body;
    }

    /**
     * Converts a given {@code ReadOnlyFitNus} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyFitNus source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        exercises.addAll(source.getExerciseList().stream().map(JsonAdaptedExercise::new).collect(Collectors.toList()));
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new).collect(Collectors.toList()));
        routines.addAll(source.getRoutineList().stream().map(JsonAdaptedRoutine::new).collect(Collectors.toList()));
        slots.addAll(source.getSlotList().stream().map(JsonAdaptedSlot::new).collect(Collectors.toList()));
        dailyCalories.addAll(source.getDailyCalorieList().stream().map(JsonAdaptedDailyCalorie::new)
                .collect(Collectors.toList()));
        body = source.getBody().stream().map(JsonAdaptedBody::new).collect(Collectors.toList()).get(0);
    }

    /**
     * Converts this address book into the model's {@code FitNus} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FitNus toModelType() throws IllegalValueException {
        FitNus fitNus = new FitNus();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (fitNus.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            fitNus.addPerson(person);
        }
        for (JsonAdaptedExercise jsonAdaptedExercise : exercises) {
            Exercise exercise = jsonAdaptedExercise.toModelType();
            if (fitNus.hasExercise(exercise)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            fitNus.addExercise(exercise);
        }
        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            if (fitNus.hasLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            fitNus.addLesson(lesson);
        }
        for (JsonAdaptedRoutine jsonAdaptedRoutine : routines) {
            Routine routine = jsonAdaptedRoutine.toModelType();
            if (fitNus.hasRoutine(routine)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ROUTINE);
            }
            fitNus.addRoutine(routine);
        }
        for (JsonAdaptedSlot jsonAdaptedSlot : slots) {
            Slot slot = jsonAdaptedSlot.toModelType();
            if (fitNus.hasSlot(slot)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SLOT);
            }
            fitNus.addSlotToTimetable(slot);
        }

        List<DailyCalorie> calorieLog = new ArrayList<>();

        for (JsonAdaptedDailyCalorie jsonAdaptedDailyCalorie: dailyCalories) {
            DailyCalorie dailyCalorie = jsonAdaptedDailyCalorie.toModelType();
            if (fitNus.hasDailyCalorie(dailyCalorie)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DAILYCALORIE);
            }
            calorieLog.add(dailyCalorie);
        }
        Collections.sort(calorieLog);
        fitNus.addCalorieEntries(calorieLog);

        if (body != null) {
            Body newBody = body.toModelType();
            fitNus.addHeight(newBody.getHeight());
            fitNus.addWeight(newBody.getWeight());
        }
        return fitNus;
    }

}
