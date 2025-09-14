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
  }
]);
