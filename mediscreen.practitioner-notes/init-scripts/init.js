        //use practitionernotes;
        //db.createCollection("practitionernotes");
        //db["practitionernotes"].insertMany([
        //    { "patientId": 6, "note":"Patient: TestBorderline Practitioner's notes/recommendations: Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late "},
        //    { "patientId": 1, "note":"Patient: TestBorderline Practitioner's notes/recommendations: Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late "},
        //]);
        
        // init.js
// Seeds the patient_notes collection. Each document will automatically
// get a unique _id (ObjectId) from MongoDB.

db = db.getSiblingDB('practitionernotes');   // change 'mydb' if needed

db.createCollection('practitionernotes');

db.practitionernotes.insertMany([
  {
	"id": "111231231",
    "patientId": 1,
    "note": "Initial consultation completed. Vitals are normal."
  },
  {
	"id": "212312312",
    "patientId": 2,
    "note": "Follow-up scheduled for next week. Patient reports mild headache."
  },
  {
	"id": "3123123123",
    "patientId": 3,
    "note": "Routine checkup. Recommended regular exercise and balanced diet."
  },
{
    "id": "3123123123",
    "patientId": 1,
    "note": "Patient states that they are feeling terrific Weight at or below recommended level."

  },
 {
    "id": "3123123123",
    "patientId": 1,
    "note": "Patient states that they feel tired during the day. Patient also complains about muscle aches. Lab reports Microalbumin elevated."
  },
{
    "id": "3123123123",
    "patientId": 1,
    "note": "Patient states that they not feeling as tired Smoker, quit within last year. Lab results indicate Antibodies present elevated."
  },
{
    "id": "3123123123",
    "patientId": 2,
    "note": "Patient states that they are feeling a great deal of stress at work. Patient also complains that their hearing seems Abnormal as of late."
  },
{
    "id": "3123123123",
    "patientId": 2,
    "note": "Patient states that they have had a Reaction to medication within last 3 months. Patient also complains that their hearing continues to be Abnormal."
  },
{
    "id": "3123123123",
    "patientId": 2,
    "note": "Lab reports Microalbumin elevated."
  },
{
    "id": "3123123123",
    "patientId": 2,
    "note": "Patient states that everything seems fine. Lab reports Hemoglobin A1C above recommended level. Patient admits to being long term Smoker."
  },
{
    "id": "3123123123",
    "patientId": 3,
    "note": "Patient states that they are short term Smoker."
  },
{
    "id": "3123123123",
    "patientId": 3,
    "note": "Lab reports Microalbumin elevated."
  },
{
    "id": "3123123123",
    "patientId": 3,
    "note": "Patient states that they are a Smoker, quit within last year. Patient also complains that of Abnormal breathing spells. Lab reports Cholesterol LDL high."
  },
{
    "id": "3123123123",
    "patientId": 3,
    "note": "Lab reports Cholesterol LDL high."
  },
{
    "id": "3123123123",
    "patientId": 4,
    "note": "Patient states that walking up stairs has become difficult. Patient also complains that they are having shortness of breath. Lab results indicate Antibodies present elevated. Reaction to medication"
  },
{
    "id": "3123123123",
    "patientId": 4,
    "note": "Patient states that they are experiencing back pain when seated for a long time"
  },
{
    "id": "3123123123",
    "patientId": 4,
    "note": "Patient states that they are a short term Smoker. Hemoglobin A1C above recommended level"
  },
{
    "id": "3123123123",
    "patientId": 5,
    "note": "Patient states that they experiencing occasional neck pain. Patient also complains that certain foods now taste different. Apparent Reaction to medication. Body Weight above recommended level"
  },
{
    "id": "3123123123",
    "patientId": 5,
    "note": "Patient states that they had multiple dizziness episodes since last visit. Body Height within concerned level"
  },
{
    "id": "3123123123",
    "patientId": 5,
    "note": "Patient states that they are still experiencing occaisional neck pain. Lab reports Microalbumin elevated. Smoker, quit within last year."
  },
{
    "id": "3123123123",
    "patientId": 5,
    "note": "Patient states that they had multiple dizziness episodes since last visit. Lab results indicate Antibodies present elevated."
  },
{
    "id": "3123123123",
    "patientId": 6,
    "note": "Patient states that they feel fine. Body Weight above recommended level."
  },
{
    "id": "3123123123",
    "patientId": 6,
    "note": "Patient states that they feel fine"
  },
{
    "id": "3123123123",
    "patientId": 7,
    "note": "Patient states that they often wake with stiffness in joints. Patient also complains that they are having difficulty sleeping. Body Weight above recommended level. Lab reports Cholesterol LDL high."
  },
{
    "id": "3123123123",
    "patientId": 8,
    "note": "Lab results indicate Antibodies present elevated. Hemoglobin A1C above recommended level."
  },
{
    "id": "3123123123",
    "patientId": 9,
    "note": "Patient states that they are having trouble concentrating on school assignments. Hemoglobin A1C above recommended level."
  },
{
    "id": "3123123123",
    "patientId": 9,
    "note": "Patient states that they frustrated as long wait times. Patient also complains that food in the vending machine is sub-par. Lab reports Abnormal blood cell levels."
  },
{
    "id": "3123123123",
    "patientId": 9,
    "note": "Patient states that they are easily irritated at minor things. Patient also complains that neighbors vacuuming is too loud. Lab results indicate Antibodies present elevated."
  },
{
    "id": "3123123123",
    "patientId": 10,
    "note": "Patient states that they are not experiencing any problems."
  },
{
    "id": "3123123123",
    "patientId": 10,
    "note": "Patient states that they are not experiencing any problems. Body Height within concerned level. Hemoglobin A1C above recommended level."
  },
{
    "id": "3123123123",
    "patientId": 10,
    "note": "Patient states that they are not experiencing any problems. Body Weight above recommended level. Patient reports multiple dizziness episodes since last visit."
  },
{
    "id": "3123123123",
    "patientId": 10,
    "note": "Patient states that they are not experiencing any problems. Lab reports Microalbumin elevated."
  }
]);
