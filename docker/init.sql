-- ####################################
-- ## Create a structure of database ##
-- ####################################

-- ## Create tables without foreign keys. ##
CREATE TABLE OpeningHours (
    id SERIAL PRIMARY KEY,
    monday_from TIME NULL, monday_to TIME NULL,
    tuesday_from TIME NULL, tuesday_to TIME NULL,
    wednesday_from TIME NULL, wednesday_to TIME NULL,
    thursday_from TIME NULL, thursday_to TIME NULL,
    friday_from TIME NULL, friday_to TIME NULL,
    saturday_from TIME NULL, saturday_to TIME NULL,
    sunday_from TIME NULL, sunday_to TIME NULL
);

CREATE TABLE Contact (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    is_primary BOOLEAN NOT NULL
);

CREATE TABLE Account (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    picture_url VARCHAR(255) NOT NULL,
    is_verified BOOLEAN NOT NULL
);

-- todo: I am here
CREATE TABLE Pet (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    species VARCHAR(255) NULL,
    breed VARCHAR(255) NULL,
    date_of_birth DATE NULL,
    weight_kg DECIMAL(10, 2) NULL,
    color VARCHAR(255) NULL,
    sterilized BOOLEAN NULL,
    picture_url VARCHAR(255) NOT NULL,
    microchip_number VARCHAR(255) NULL,
    medical_notes VARCHAR(255) NULL
);

CREATE TABLE Invoice (
    id SERIAL PRIMARY KEY,
    invoice_date DATE NOT NULL,
    due_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    amount_paid DECIMAL(10, 2) NOT NULL,
    outstanding_amount DECIMAL(10, 2) NOT NULL,
    payment_status VARCHAR(9) NOT NULL, -- pending, completed, failed, cancelled, refunded
    payment_method VARCHAR(14) NOT NULL, -- card_payment, bank_transfer, mobile_payment, cash_payment
    notes VARCHAR(255) NOT NULL
);

CREATE TABLE Service (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    category VARCHAR(255) NOT NULL,
    duration_minutes INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    is_available BOOLEAN NOT NULL
);

CREATE TABLE MedicalRecord (
    id SERIAL PRIMARY KEY,
    diagnosis TEXT NOT NULL,
    treatment TEXT NOT NULL,
    procedures TEXT NOT NULL,
    next_appointment DATE NOT NULL,
    status VARCHAR(11) NOT NULL, --scheduled, in_progress, completed, cancelled, no_show, rescheduled
    notes TEXT NOT NULL
);

-- ## Create tables with foreign keys. ##
CREATE TABLE Clinic (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    opening_hours_id INTEGER NOT NULL,
    FOREIGN KEY (opening_hours_id) REFERENCES OpeningHours(id)
);

-- todo: I am here
CREATE TABLE Medication (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    dose TEXT NOT NULL,
    quantity_in_stock INTEGER NOT NULL,
    expiration_date DATE NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    is_available BOOLEAN NOT NULL,
    clinic_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id)
);
---- Clinic id = 1
---- id = 1
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Pain Relief Tablets', 'Pain reliever for mild to moderate pain', 'HealthCo', '500mg', 100, '2026-12-31', 20.00, TRUE, 1);
---- id = 2
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Antibiotic Ointment', 'Topical ointment for skin infections', 'MedCorp', '5g', 50, '2025-11-15', 10.50, TRUE, 1);
---- id = 3
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Flea Prevention Drops', 'Monthly flea prevention for dogs and cats', 'VetPharma', '2ml', 200, '2025-09-20', 30.00, TRUE, 1);
---- id = 4
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Heartworm Medication', 'Prevention for heartworm in dogs', 'PetCare Inc.', '10mg', 150, '2025-10-05', 25.00, TRUE, 1);
---- id = 5
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Vaccination Booster', 'Annual vaccination booster for dogs', 'BioVax', '1 dose', 80, '2026-05-12', 50.00, TRUE, 1);
---- id = 6
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Dental Gel', 'Gel for improving oral health and reducing plaque', 'PetMed', '100ml', 60, '2026-02-01', 15.00, TRUE, 1);
---- id = 7
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Eye Drops', 'Lubricating eye drops for dry eyes', 'OcularMed', '10ml', 120, '2025-08-15', 12.00, TRUE, 1);
---- id = 8
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('De-wormer', 'Medication for de-worming pets', 'WormFree', '200mg', 200, '2025-07-30', 18.00, TRUE, 1);
---- id = 9
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Joint Support Supplement', 'Supplement to support joint health in older pets', 'JointCare', '500mg', 50, '2026-01-01', 22.50, TRUE, 1);
---- id = 10
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Antihistamine Tablets', 'Antihistamine for allergy relief in pets', 'AllerPet', '25mg', 100, '2025-12-12', 16.00, TRUE, 1);
---- id = 11
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Calming Chews', 'Natural chews to reduce anxiety in pets', 'CalmPet', '30 chews', 75, '2026-03-10', 27.00, TRUE, 1);
---- id = 12
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Vitamin Supplement', 'Daily vitamin supplement for dogs', 'NutriPets', '1 tablet', 200, '2025-10-18', 10.00, TRUE, 1);
---- Clinic id = 2
---- id = 13
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Pain Relief Tablets', 'Pain reliever for mild to moderate pain', 'HealthCo', '500mg', 90, '2025-12-31', 20.00, TRUE, 2);
---- id = 14
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Antibiotic Ointment', 'Topical ointment for skin infections', 'MedCorp', '5g', 55, '2025-10-15', 11.50, TRUE, 2);
---- id = 15
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Flea Prevention Drops', 'Monthly flea prevention for dogs and cats', 'VetPharma', '2ml', 250, '2025-09-10', 32.00, TRUE, 2);
---- id = 16
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Heartworm Medication', 'Prevention for heartworm in dogs', 'PetCare Inc.', '10mg', 160, '2025-11-05', 26.00, TRUE, 2);
---- id = 17
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Vaccination Booster', 'Annual vaccination booster for dogs', 'BioVax', '1 dose', 85, '2026-06-12', 52.00, TRUE, 2);
---- id = 18
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Dental Gel', 'Gel for improving oral health and reducing plaque', 'PetMed', '100ml', 65, '2026-02-10', 16.00, TRUE, 2);
---- id = 19
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Eye Drops', 'Lubricating eye drops for dry eyes', 'OcularMed', '10ml', 115, '2025-08-20', 12.50, TRUE, 2);
---- id = 20
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('De-wormer', 'Medication for de-worming pets', 'WormFree', '200mg', 190, '2025-07-10', 19.00, TRUE, 2);
---- id = 21
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Joint Support Supplement', 'Supplement to support joint health in older pets', 'JointCare', '500mg', 60, '2026-02-01', 23.00, TRUE, 2);
---- id = 22
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Antihistamine Tablets', 'Antihistamine for allergy relief in pets', 'AllerPet', '25mg', 105, '2025-11-18', 16.50, TRUE, 2);
---- id = 23
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Calming Chews', 'Natural chews to reduce anxiety in pets', 'CalmPet', '30 chews', 80, '2026-04-15', 28.00, TRUE, 2);
---- id = 24
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Vitamin Supplement', 'Daily vitamin supplement for dogs', 'NutriPets', '1 tablet', 210, '2025-09-20', 11.00, TRUE, 2);
---- Clinic id = 3
---- id = 25
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Arthritis Relief Gel', 'Topical gel for arthritis pain relief in dogs and cats', 'PetPharma', '50g', 120, '2026-02-28', 23.00, TRUE, 3);
---- id = 26
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Allergy Relief Chews', 'Natural chews for seasonal allergy relief', 'NaturePets', '30 chews', 150, '2025-11-25', 18.50, TRUE, 3);
---- id = 27
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Probiotic Supplement', 'Probiotic for improving digestion and gut health', 'PetHealth', '1 capsule', 200, '2025-12-15', 14.00, TRUE, 3);
---- id = 28
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Tick Prevention Collar', 'Tick prevention collar for dogs and cats', 'TickGuard', '1 collar', 50, '2026-03-10', 35.00, TRUE, 3);
---- id = 29
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Puppy Vaccination Set', 'Vaccination set for puppies, first round', 'VaxPet', '1 dose', 90, '2026-06-30', 55.00, TRUE, 3);
---- id = 30
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Ear Cleanser', 'Solution for cleaning pet ears and preventing infections', 'CleanPet', '100ml', 80, '2025-10-05', 12.00, TRUE, 3);
---- id = 31
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Wound Healing Ointment', 'Ointment to promote faster wound healing in pets', 'HealMed', '10g', 60, '2026-01-10', 22.00, TRUE, 3);
---- id = 32
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Weight Management Food', 'Special diet food for weight management in pets', 'PetNutra', '2kg bag', 120, '2025-08-15', 28.00, TRUE, 3);
---- id = 33
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Hairball Remedy', 'Chews for preventing hairballs in cats', 'FelineCare', '30 chews', 95, '2026-04-20', 16.50, TRUE, 3);
---- id = 34
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Calcium Supplement', 'Supplement for bone health in pets', 'BoneCare', '250mg', 110, '2025-11-10', 11.00, TRUE, 3);
---- id = 35
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Liver Support Supplement', 'Liver support for older pets', 'LiverPet', '500mg', 130, '2026-02-01', 19.50, TRUE, 3);
---- id = 36
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Vitamin Supplement', 'Daily vitamin supplement for dogs', 'NutriPets', '1 tablet', 210, '2025-09-20', 11.00, TRUE, 3);
---- Clinic id = 4
---- id = 37
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Joint Care Chews', 'Chews for joint health and mobility in older pets', 'FlexiPets', '30 chews', 130, '2026-01-10', 21.00, TRUE, 4);
---- id = 38
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Calming Drops', 'Natural drops for reducing anxiety and stress in pets', 'CalmPet', '15ml', 200, '2025-12-10', 18.00, TRUE, 4);
---- id = 39
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Digestive Aid Powder', 'Powdered supplement to aid digestion in pets', 'PetNutra', '50g', 100, '2025-11-25', 12.50, TRUE, 4);
---- id = 40
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Allergy Relief Tablets', 'Tablets for seasonal allergy relief in pets', 'AllerPet', '25mg', 150, '2025-10-15', 16.00, TRUE, 4);
---- id = 41
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Diabetes Care Supplement', 'Support for managing diabetes in pets', 'DiabetoCare', '200mg', 80, '2026-03-05', 23.00, TRUE, 4);
---- id = 42
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Skin Care Oil', 'Oil for treating dry skin and coat in pets', 'SoftSkin', '50ml', 130, '2025-12-01', 17.50, TRUE, 4);
---- id = 43
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Cough Syrup', 'Syrup to relieve coughing and respiratory issues in pets', 'PetCure', '100ml', 90, '2025-09-30', 14.00, TRUE, 4);
---- id = 44
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Heart Health Supplement', 'Supplement to support cardiovascular health in pets', 'CardioPet', '300mg', 120, '2026-02-10', 25.00, TRUE, 4);
---- id = 45
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Ear Infection Medicine', 'Medicine for treating ear infections in pets', 'EarPet', '15ml', 110, '2025-11-15', 19.00, TRUE, 4);
---- id = 46
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Multivitamin Chews', 'Chews with a blend of essential vitamins for pets', 'VitaPet', '30 chews', 200, '2025-12-05', 13.50, TRUE, 4);
---- id = 47
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Worming Paste', 'Oral paste for deworming pets', 'WormCare', '20g', 150, '2025-10-20', 22.00, TRUE, 4);
---- id = 48
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Senior Dog Food', 'Specialized dog food for older dogs', 'PetNutra', '3kg bag', 100, '2025-09-25', 28.50, TRUE, 4);
---- Clinic id = 5
---- id = 49
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Flea and Tick Shampoo', 'Shampoo for removing fleas and ticks from pets', 'PetClean', '200ml', 150, '2025-08-30', 18.00, TRUE, 5);
---- id = 50
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Pain Relief Tablets', 'Pain relief for pets suffering from arthritis or injury', 'PainAway', '50mg', 200, '2026-02-01', 21.00, TRUE, 5);
---- id = 51
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Teeth Cleaning Gel', 'Gel for cleaning and maintaining pet teeth', 'CleanTeeth', '100g', 100, '2025-11-15', 13.50, TRUE, 5);
---- id = 52
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Pet Vitamin Paste', 'Daily vitamin paste for enhancing general health', 'VitaPaste', '50g', 80, '2025-12-25', 15.00, TRUE, 5);
---- id = 53
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Cataract Treatment Drops', 'Eye drops for cataract prevention in pets', 'VisionPets', '10ml', 60, '2026-01-20', 28.00, TRUE, 5);
---- id = 54
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Lung Health Supplement', 'Supplement to support lung health in pets with respiratory issues', 'AirCare', '200mg', 150, '2025-10-30', 20.00, TRUE, 5);
---- id = 55
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Hair Growth Shampoo', 'Shampoo designed to promote hair growth in pets with thinning coats', 'HairBoost', '250ml', 90, '2025-11-05', 25.00, TRUE, 5);
---- id = 56
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Antibiotic Ointment', 'Topical antibiotic ointment for wound care', 'PetMed', '20g', 130, '2026-03-01', 17.50, TRUE, 5);
---- id = 57
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Cough Suppressant Syrup', 'Syrup for reducing coughing in pets with respiratory infections', 'CoughFree', '100ml', 110, '2025-10-10', 14.00, TRUE, 5);
---- id = 58
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Joint Mobility Oil', 'Oil for improving joint mobility in older pets', 'FlexOil', '50ml', 150, '2025-12-10', 22.00, TRUE, 5);
---- id = 59
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Anti-Diarrheal Tablets', 'Tablets for controlling diarrhea in pets', 'PetCare', '25mg', 180, '2025-11-05', 19.00, TRUE, 5);
---- id = 60
--INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id)
--VALUES ('Pet Immune Booster', 'Immune booster supplement for pets', 'ImmunoPet', '1 capsule', 140, '2026-01-15', 21.50, TRUE, 5);

CREATE TABLE Vet (
    id SERIAL PRIMARY KEY,
    salary DECIMAL(10, 2) NOT NULL,
    license_number VARCHAR(255) NOT NULL,
    license_issue_date DATE NOT NULL,
    license_expiry_date DATE NOT NULL,
    specializations VARCHAR(255) NOT NULL,
    years_of_experience INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    opening_hours_id INTEGER NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Account(id),
    FOREIGN KEY (opening_hours_id) REFERENCES OpeningHours(id)
);

CREATE TABLE Client (
    id SERIAL PRIMARY KEY,
    account_id INTEGER NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE Appointment (
    id SERIAL PRIMARY KEY,
    date_and_hour TIMESTAMP NOT NULL,
    type VARCHAR(255) NOT NULL,
    status VARCHAR(11) NOT NULL, --scheduled, in_progress, completed, cancelled, no_show, rescheduled
    reason TEXT NOT NULL,
    notes TEXT NOT NULL,
    clinic_id INTEGER NOT NULL,
    vet_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    pet_id INTEGER NOT NULL,
    invoice_id INTEGER NOT NULL,
    medical_record_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id),
    FOREIGN KEY (vet_id) REFERENCES Vet(id),
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (pet_id) REFERENCES Pet(id),
    FOREIGN KEY (invoice_id) REFERENCES Invoice(id),
    FOREIGN KEY (medical_record_id) REFERENCES MedicalRecord(id)
);

-- ## Create tables with relations between them. ##
CREATE TABLE Clinic_Contact (
    id SERIAL PRIMARY KEY,
    clinic_id INTEGER NOT NULL,
    contact_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id),
    FOREIGN KEY (contact_id) REFERENCES Contact(id)
);

CREATE TABLE Clinic_Account (
    id SERIAL PRIMARY KEY,
    clinic_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id),
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

-- todo: I am here
CREATE TABLE Owner (
    id SERIAL PRIMARY KEY,
    clinic_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id),
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE Account_Contact (
    id SERIAL PRIMARY KEY,
    account_id INTEGER NOT NULL,
    contact_id INTEGER NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Account(id),
    FOREIGN KEY (contact_id) REFERENCES Contact(id)
);

CREATE TABLE Client_Pet (
    id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    pet_id INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (pet_id) REFERENCES Pet(id)
);

CREATE TABLE Service_Clinic (
    id SERIAL PRIMARY KEY,
    service_id INTEGER NOT NULL,
    clinic_id INTEGER NOT NULL,
    FOREIGN KEY (service_id) REFERENCES Service(id),
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id)
);

CREATE TABLE Appointment_Service (
    id SERIAL PRIMARY KEY,
    appointment_id INTEGER NOT NULL,
    service_id INTEGER NOT NULL,
    FOREIGN KEY (appointment_id) REFERENCES Appointment(id),
    FOREIGN KEY (service_id) REFERENCES Service(id)
);

CREATE TABLE MedicalRecord_Medication (
    id SERIAL PRIMARY KEY,
    medication_quantity INTEGER NOT NULL,
    medical_record_id INTEGER NOT NULL,
    medication_id INTEGER NOT NULL,
    FOREIGN KEY (medical_record_id) REFERENCES MedicalRecord(id),
    FOREIGN KEY (medication_id) REFERENCES Medication(id)
);

-- ########################
-- ## Create a test data ##
-- ########################

-- ## Insert data to tables without foreign keys. ##
INSERT INTO OpeningHours (
    monday_from, monday_to,
    tuesday_from, tuesday_to,
    wednesday_from, wednesday_to,
    thursday_from, thursday_to,
    friday_from, friday_to,
    saturday_from, saturday_to,
    sunday_from, sunday_to
) VALUES
    -- id = 1, Clinic id = 1
    (
        '08:00:00', '16:00:00',
        '08:00:00', '16:00:00',
        '08:00:00', '16:00:00',
        '08:00:00', '16:00:00',
        '08:00:00', '16:00:00',
        '08:00:00', '12:00:00',
        NULL, NULL
    ),
    -- id = 2, Clinic id = 2
    (
        '09:00:00', '17:00:00',
        '09:00:00', '17:00:00',
        '09:00:00', '17:00:00',
        '09:00:00', '17:00:00',
        '10:00:00', '14:00:00',
        '10:00:00', '14:00:00',
        NULL, NULL
    ),
    -- id = 3, Clinic id = 3
    (
        '12:00:00', '20:00:00',
        '12:00:00', '20:00:00',
        '12:00:00', '20:00:00',
        '12:00:00', '20:00:00',
        NULL, NULL,
        '12:00:00', '18:00:00',
        NULL, NULL
    ),
    -- id = 4, Clinic id = 4
    (
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '14:00:00',
        '10:00:00', '14:00:00'
    ),
    -- id = 5, Clinic id = 5
    (
        '07:30:00', '19:00:00',
        '07:30:00', '19:00:00',
        '07:30:00', '19:00:00',
        '07:30:00', '19:00:00',
        '07:30:00', '19:00:00',
        '09:00:00', '14:00:00',
        '09:00:00', '12:00:00'
    );

INSERT INTO Contact (type, value, is_primary) VALUES
    -- id = 1, Clinic id = 1
    ('email', 'contact@happypawsvet.com', TRUE),
    -- id = 2, Clinic id = 1
    ('phone', '555-123-456', TRUE),
    -- id = 3, Clinic id = 1
    ('emergency_phone', '555-999-000', TRUE),
    -- id = 4, Clinic id = 2
    ('email', 'contact@healthypetsclinic.com', TRUE),
    -- id = 5, Clinic id = 2
    ('phone', '555-567-890', TRUE),
    -- id = 6, Clinic id = 2
    ('emergency_phone', '555-888-112', TRUE),
    -- id = 7, Clinic id = 3
    ('email', 'contact@fourpawsveterinary.com', TRUE),
    -- id = 8, Clinic id = 3
    ('phone', '555-234-567', TRUE),
    -- id = 9, Clinic id = 3
    ('emergency_phone', '555-111-223', TRUE),
    -- id = 10, Clinic id = 4
    ('email', 'contact@caringhandsvet.com', TRUE),
    -- id = 11, Clinic id = 4
    ('phone', '555-678-901', TRUE),
    -- id = 12, Clinic id = 4
    ('emergency_phone', '555-222-334', TRUE),
    -- id = 13, Clinic id = 5
    ('email', 'contact@animalwellness.com', TRUE),
    -- id = 14, Clinic id = 5
    ('phone', '555-345-678', TRUE),
    -- id = 15, Clinic id = 5
    ('emergency_phone', '555-333-445', TRUE),
    -- id = 16, Account id = 1
    ('email', 'adam.smith@gmail.com', TRUE),
    -- id = 17, Account id = 1
    ('phone', '555-100-001', TRUE),
    -- id = 18, Account id = 2
    ('email', 'barbara.johnson@yahoo.com', TRUE),
    -- id = 19, Account id = 2
    ('phone', '555-100-002', TRUE),
    -- id = 20, Account id = 3
    ('email', 'charles.brown@outlook.com', TRUE),
    -- id = 21, Account id = 3
    ('phone', '555-100-003', TRUE),
    -- id = 22, Account id = 4
    ('email', 'diana.miller@gmail.com', TRUE),
    -- id = 23, Account id = 4
    ('phone', '555-100-004', TRUE),
    -- id = 24, Account id = 5
    ('email', 'eric.wilson@aol.com', TRUE),
    -- id = 25, Account id = 5
    ('phone', '555-100-005', TRUE),
    -- id = 26, Account id = 6
    ('email', 'frank.thomas@mail.com', TRUE),
    -- id = 27, Account id = 6
    ('phone', '555-100-006', TRUE),
    -- id = 28, Account id = 7
    ('email', 'grace.moore@gmail.com', TRUE),
    -- id = 29, Account id = 7
    ('phone', '555-100-007', TRUE),
    -- id = 30, Account id = 8
    ('email', 'henry.taylor@live.com', TRUE),
    -- id = 31, Account id = 8
    ('phone', '555-100-008', TRUE),
    -- id = 32, Account id = 9
    ('email', 'isabella.anderson@icloud.com', TRUE),
    -- id = 33, Account id = 9
    ('phone', '555-100-009', TRUE),
    -- id = 34, Account id = 10
    ('email', 'jackson.white@gmail.com', TRUE),
    -- id = 35, Account id = 10
    ('phone', '555-100-010', TRUE),
    -- id = 36, Account id = 11
    ('email', 'kate.harris@yahoo.com', TRUE),
    -- id = 37, Account id = 11
    ('phone', '555-100-011', TRUE),
    -- id = 38, Account id = 12
    ('email', 'leo.martin@outlook.com', TRUE),
    -- id = 39, Account id = 12
    ('phone', '555-100-012', TRUE),
    -- id = 40, Account id = 13
    ('email', 'mia.thompson@gmail.com', TRUE),
    -- id = 41, Account id = 13
    ('phone', '555-100-013', TRUE),
    -- id = 42, Account id = 14
    ('email', 'nathan.garcia@mail.com', TRUE),
    -- id = 43, Account id = 14
    ('phone', '555-100-014', TRUE),
    -- id = 44, Account id = 15
    ('email', 'olivia.clark@aol.com', TRUE),
    -- id = 45, Account id = 15
    ('phone', '555-100-015', TRUE),
    -- id = 46, Account id = 16
    ('email', 'peter.rodriguez@gmail.com', TRUE),
    -- id = 47, Account id = 16
    ('phone', '555-100-016', TRUE),
    -- id = 48, Account id = 17
    ('email', 'quinn.lewis@yahoo.com', TRUE),
    -- id = 49, Account id = 17
    ('phone', '555-100-017', TRUE),
    -- id = 50, Account id = 18
    ('email', 'rachel.walker@outlook.com', TRUE),
    -- id = 51, Account id = 18
    ('phone', '555-100-018', TRUE),
    -- id = 52, Account id = 19
    ('email', 'samuel.hall@gmail.com', TRUE),
    -- id = 53, Account id = 19
    ('phone', '555-100-019', TRUE),
    -- id = 54, Account id = 20
    ('email', 'tina.allen@mail.com', TRUE),
    -- id = 55, Account id = 20
    ('phone', '555-100-020', TRUE),
    -- id = 56, Account id = 21
    ('email', 'ulysses.young@gmail.com', TRUE),
    -- id = 57, Account id = 21
    ('phone', '555-100-021', TRUE),
    -- id = 58, Account id = 22
    ('email', 'victoria.king@yahoo.com', TRUE),
    -- id = 59, Account id = 22
    ('phone', '555-100-022', TRUE),
    -- id = 60, Account id = 23
    ('email', 'william.scott@outlook.com', TRUE),
    -- id = 61, Account id = 23
    ('phone', '555-100-023', TRUE),
    -- id = 62, Account id = 24
    ('email', 'xavier.green@gmail.com', TRUE),
    -- id = 63, Account id = 24
    ('phone', '555-100-024', TRUE),
    -- id = 64, Account id = 25
    ('email', 'yasmine.adams@icloud.com', TRUE),
    -- id = 65, Account id = 25
    ('phone', '555-100-025', TRUE),
    -- id = 66, Account id = 26
    ('email', 'zachary.baker@yahoo.com', TRUE),
    -- id = 67, Account id = 26
    ('phone', '555-100-026', TRUE),
    -- id = 68, Account id = 27
    ('email', 'amelia.bell@gmail.com', TRUE),
    -- id = 69, Account id = 27
    ('phone', '555-100-027', TRUE),
    -- id = 70, Account id = 28
    ('email', 'benjamin.brooks@outlook.com', TRUE),
    -- id = 71, Account id = 28
    ('phone', '555-100-028', TRUE),
    -- id = 72, Account id = 29
    ('email', 'catherine.carter@gmail.com', TRUE),
    -- id = 73, Account id = 29
    ('phone', '555-100-029', TRUE),
    -- id = 74, Account id = 30
    ('email', 'daniel.davis@mail.com', TRUE),
    -- id = 75, Account id = 30
    ('phone', '555-100-030', TRUE);


INSERT INTO Account (email, password, first_name, last_name, role, picture_url, is_verified) VALUES
    -- id = 1, vet, Clinic id = 1
    ('adam.smith@gmail.com', 'VetPass1!', 'Adam', 'Smith', 'vet', 'https://example.com/pic1.jpg', TRUE),
    -- id = 2, vet, Clinic id = 1
    ('barbara.johnson@yahoo.com', 'VetPass2!', 'Barbara', 'Johnson', 'vet', 'https://example.com/pic2.jpg', TRUE),
    -- id = 3, vet, Clinic id = 1
    ('charles.brown@outlook.com', 'VetPass3!', 'Charles', 'Brown', 'vet', 'https://example.com/pic3.jpg', TRUE),
    -- id = 4, client, Clinic id = 1
    ('diana.miller@gmail.com', 'ClientPass1!', 'Diana', 'Miller', 'client', 'https://example.com/pic4.jpg', TRUE),
    -- id = 5, client, Clinic id = 1
    ('eric.wilson@aol.com', 'ClientPass2!', 'Eric', 'Wilson', 'client', 'https://example.com/pic5.jpg', TRUE),
    -- id = 6, client, Clinic id = 1
    ('frank.thomas@mail.com', 'ClientPass3!', 'Frank', 'Thomas', 'client', 'https://example.com/pic6.jpg', TRUE),
    -- id = 7, vet, Clinic id = 2
    ('grace.moore@gmail.com', 'VetPass4!', 'Grace', 'Moore', 'vet', 'https://example.com/pic7.jpg', TRUE),
    -- id = 8, vet, Clinic id = 2
    ('henry.taylor@live.com', 'VetPass5!', 'Henry', 'Taylor', 'vet', 'https://example.com/pic8.jpg', TRUE),
    -- id = 9, vet, Clinic id = 2
    ('isabella.anderson@icloud.com', 'VetPass6!', 'Isabella', 'Anderson', 'vet', 'https://example.com/pic9.jpg', TRUE),
    -- id = 10, client, Clinic id = 2
    ('jackson.white@gmail.com', 'ClientPass4!', 'Jackson', 'White', 'client', 'https://example.com/pic10.jpg', TRUE),
    -- id = 11, client, Clinic id = 2
    ('kate.harris@yahoo.com', 'ClientPass5!', 'Kate', 'Harris', 'client', 'https://example.com/pic11.jpg', TRUE),
    -- id = 12, client, Clinic id = 2
    ('leo.martin@outlook.com', 'ClientPass6!', 'Leo', 'Martin', 'client', 'https://example.com/pic12.jpg', TRUE),
    -- id = 13, vet, Clinic id = 3
    ('mia.thompson@gmail.com', 'VetPass7!', 'Mia', 'Thompson', 'vet', 'https://example.com/pic13.jpg', TRUE),
    -- id = 14, vet, Clinic id = 3
    ('nathan.garcia@mail.com', 'VetPass8!', 'Nathan', 'Garcia', 'vet', 'https://example.com/pic14.jpg', TRUE),
    -- id = 15, vet, Clinic id = 3
    ('olivia.clark@aol.com', 'VetPass9!', 'Olivia', 'Clark', 'vet', 'https://example.com/pic15.jpg', TRUE),
    -- id = 16, client, Clinic id = 3
    ('peter.rodriguez@gmail.com', 'ClientPass7!', 'Peter', 'Rodriguez', 'client', 'https://example.com/pic16.jpg', TRUE),
    -- id = 17, client, Clinic id = 3
    ('quinn.lewis@yahoo.com', 'ClientPass8!', 'Quinn', 'Lewis', 'client', 'https://example.com/pic17.jpg', TRUE),
    -- id = 18, client, Clinic id = 3
    ('rachel.walker@outlook.com', 'ClientPass9!', 'Rachel', 'Walker', 'client', 'https://example.com/pic18.jpg', TRUE),
    -- id = 19, vet, Clinic id = 4
    ('samuel.hall@gmail.com', 'VetPass10!', 'Samuel', 'Hall', 'vet', 'https://example.com/pic19.jpg', TRUE),
    -- id = 20, vet, Clinic id = 4
    ('tina.allen@mail.com', 'VetPass11!', 'Tina', 'Allen', 'vet', 'https://example.com/pic20.jpg', TRUE),
    -- id = 21, vet, Clinic id = 4
    ('ulysses.young@gmail.com', 'VetPass12!', 'Ulysses', 'Young', 'vet', 'https://example.com/pic21.jpg', TRUE),
    -- id = 22, client, Clinic id = 4
    ('victoria.king@yahoo.com', 'ClientPass10!', 'Victoria', 'King', 'client', 'https://example.com/pic22.jpg', TRUE),
    -- id = 23, client, Clinic id = 4
    ('william.scott@outlook.com', 'ClientPass11!', 'William', 'Scott', 'client', 'https://example.com/pic23.jpg', TRUE),
    -- id = 24, client, Clinic id = 4
    ('xavier.green@gmail.com', 'ClientPass12!', 'Xavier', 'Green', 'client', 'https://example.com/pic24.jpg', TRUE),
    -- id = 25, vet, Clinic id = 5
    ('yasmine.adams@icloud.com', 'VetPass13!', 'Yasmine', 'Adams', 'vet', 'https://example.com/pic25.jpg', TRUE),
    -- id = 26, vet, Clinic id = 5
    ('zachary.baker@yahoo.com', 'VetPass14!', 'Zachary', 'Baker', 'vet', 'https://example.com/pic26.jpg', TRUE),
    -- id = 27, vet, Clinic id = 5
    ('amelia.bell@gmail.com', 'VetPass15!', 'Amelia', 'Bell', 'vet', 'https://example.com/pic27.jpg', TRUE),
    -- id = 28, client, Clinic id = 5
    ('benjamin.brooks@outlook.com', 'ClientPass13!', 'Benjamin', 'Brooks', 'client', 'https://example.com/pic28.jpg', TRUE),
    -- id = 29, client, Clinic id = 5
    ('catherine.carter@gmail.com', 'ClientPass14!', 'Catherine', 'Carter', 'client', 'https://example.com/pic29.jpg', TRUE),
    -- id = 30, client, Clinic id = 5
    ('daniel.davis@mail.com', 'ClientPass15!', 'Daniel', 'Davis', 'client', 'https://example.com/pic30.jpg', TRUE);

-- ## Insert data to tables with foreign keys. ##
INSERT INTO Clinic (name, address, opening_hours_id) VALUES
    -- id = 1
    ('Happy Paws Vet Clinic', '123 Main St', 1),
    -- id = 2
    ('Healthy Pets Clinic', '456 Oak St', 2),
    -- id = 3
    ('Four Paws Veterinary', '789 Pine St', 3),
    -- id = 4
    ('Caring Hands Vet', '321 Maple St', 4),
    -- id = 5
    ('Animal Wellness Center', '654 Birch St', 5);

-- ## Insert data to tables with relations between them. ##
INSERT INTO Clinic_Contact (clinic_id, contact_id) VALUES
    -- id = 1, Clinic id = 1, Contact id = 1
    (1, 1),
    -- id = 2, Clinic id = 1, Contact id = 2
    (1, 2),
    -- id = 3, Clinic id = 1, Contact id = 3
    (1, 3),
    -- id = 4, Clinic id = 2, Contact id = 4
    (2, 4),
    -- id = 5, Clinic id = 2, Contact id = 5
    (2, 5),
    -- id = 6, Clinic id = 2, Contact id = 6
    (2, 6),
    -- id = 7, Clinic id = 3, Contact id = 7
    (3, 7),
    -- id = 8, Clinic id = 3, Contact id = 8
    (3, 8),
    -- id = 9, Clinic id = 3, Contact id = 9
    (3, 9),
    -- id = 10, Clinic id = 4, Contact id = 10
    (4, 10),
    -- id = 11, Clinic id = 4, Contact id = 11
    (4, 11),
    -- id = 12, Clinic id = 4, Contact id = 12
    (4, 12),
    -- id = 13, Clinic id = 5, Contact id = 13
    (5, 13),
    -- id = 14, Clinic id = 5, Contact id = 14
    (5, 14),
    -- id = 15, Clinic id = 5, Contact id = 15
    (5, 15);

INSERT INTO Clinic_Account (clinic_id, account_id) VALUES
    -- id = 1, Clinic id = 1, Account id = 1
    (1, 1),
    -- id = 1, Clinic id = 1, Account id = 2
    (1, 2),
    -- id = 1, Clinic id = 1, Account id = 3
    (1, 3),
    -- id = 1, Clinic id = 1, Account id = 4
    (1, 4),
    -- id = 1, Clinic id = 1, Account id = 5
    (1, 5),
    -- id = 1, Clinic id = 1, Account id = 6
    (1, 6),
    -- id = 2, Clinic id = 2, Account id = 7
    (2, 7),
    -- id = 2, Clinic id = 2, Account id = 8
    (2, 8),
    -- id = 2, Clinic id = 2, Account id = 9
    (2, 9),
    -- id = 2, Clinic id = 2, Account id = 10
    (2, 10),
    -- id = 2, Clinic id = 2, Account id = 11
    (2, 11),
    -- id = 2, Clinic id = 2, Account id = 12
    (2, 12),
    -- id = 3, Clinic id = 3, Account id = 13
    (3, 13),
    -- id = 3, Clinic id = 3, Account id = 14
    (3, 14),
    -- id = 3, Clinic id = 3, Account id = 15
    (3, 15),
    -- id = 3, Clinic id = 3, Account id = 16
    (3, 16),
    -- id = 3, Clinic id = 3, Account id = 17
    (3, 17),
    -- id = 3, Clinic id = 3, Account id = 18
    (3, 18),
    -- id = 4, Clinic id = 4, Account id = 19
    (4, 19),
    -- id = 4, Clinic id = 4, Account id = 20
    (4, 20),
    -- id = 4, Clinic id = 4, Account id = 21
    (4, 21),
    -- id = 4, Clinic id = 4, Account id = 22
    (4, 22),
    -- id = 4, Clinic id = 4, Account id = 23
    (4, 23),
    -- id = 4, Clinic id = 4, Account id = 24
    (4, 24),
    -- id = 5, Clinic id = 5, Account id = 25
    (5, 25),
    -- id = 5, Clinic id = 5, Account id = 26
    (5, 26),
    -- id = 5, Clinic id = 5, Account id = 27
    (5, 27),
    -- id = 5, Clinic id = 5, Account id = 28
    (5, 28),
    -- id = 5, Clinic id = 5, Account id = 29
    (5, 29),
    -- id = 5, Clinic id = 5, Account id = 30
    (5, 30);
