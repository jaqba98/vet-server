-- ####################################
-- ## Create a structure of database ##
-- ####################################

-- ## Create tables without foreign keys. ##
CREATE TABLE OpeningHours (
    id SERIAL PRIMARY KEY,
    monday_from TIME NULL,
    monday_to TIME NULL,
    tuesday_from TIME NULL,
    tuesday_to TIME NULL,
    wednesday_from TIME NULL,
    wednesday_to TIME NULL,
    thursday_from TIME NULL,
    thursday_to TIME NULL,
    friday_from TIME NULL,
    friday_to TIME NULL,
    saturday_from TIME NULL,
    saturday_to TIME NULL,
    sunday_from TIME NULL,
    sunday_to TIME NULL
);

CREATE TABLE Contact (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    is_primary BOOLEAN NOT NULL
);

CREATE TABLE Account (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    role VARCHAR(6) NULL,
    picture_url VARCHAR(255) NULL
);

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

CREATE TABLE MedicalRecord (
    id SERIAL PRIMARY KEY,
    diagnosis TEXT NOT NULL,
    treatment TEXT NOT NULL,
    procedures TEXT NOT NULL,
    next_appointment DATE NOT NULL,
    status VARCHAR(11) NOT NULL,
    notes TEXT NOT NULL
);

CREATE TABLE Invoice (
    id SERIAL PRIMARY KEY,
    invoice_date DATE NOT NULL,
    due_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    amount_paid DECIMAL(10, 2) NOT NULL,
    outstanding_amount DECIMAL(10, 2) NOT NULL,
    payment_status VARCHAR(9) NOT NULL,
    payment_method VARCHAR(14) NOT NULL,
    notes VARCHAR(255) NOT NULL
);

-- ## Create tables with foreign keys. ##
CREATE TABLE Clinic (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL UNIQUE,
    street VARCHAR(100) NOT NULL,
    building_number VARCHAR(10) NOT NULL,
    apartment_number VARCHAR(10) NULL,
    postal_code VARCHAR(10) NOT NULL,
    city VARCHAR(80) NOT NULL,
    province VARCHAR(80) NOT NULL,
    country VARCHAR(56) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(10) NOT NULL,
    opening_hours_id INTEGER NOT NULL,
    FOREIGN KEY (opening_hours_id) REFERENCES OpeningHours(id)
);

CREATE TABLE Service (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    duration_minutes INTEGER NOT NULL,
    price VARCHAR(255) NOT NULL,
    is_available VARCHAR(255) NOT NULL,
    clinic_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id)
);

CREATE TABLE Vet (
    id SERIAL PRIMARY KEY,
    license_number VARCHAR(255) NULL,
    license_issue_date DATE NULL,
    license_expiry_date DATE NULL,
    specializations VARCHAR(255) NULL,
    years_of_experience INTEGER NULL,
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

CREATE TABLE Appointment (
    id SERIAL PRIMARY KEY,
    date_and_hour TIMESTAMP NOT NULL,
    type VARCHAR(255) NOT NULL,
    status VARCHAR(11) NOT NULL,
    reason TEXT NOT NULL,
    notes TEXT NOT NULL,
    clinic_id INTEGER NOT NULL,
    vet_id INTEGER NOT NULL,
    pet_id INTEGER NOT NULL,
    invoice_id INTEGER NOT NULL,
    medical_record_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id),
    FOREIGN KEY (vet_id) REFERENCES Vet(id),
    FOREIGN KEY (pet_id) REFERENCES Pet(id),
    FOREIGN KEY (invoice_id) REFERENCES Invoice(id),
    FOREIGN KEY (medical_record_id) REFERENCES MedicalRecord(id)
);

-- ## Create tables with relations between them. ##
CREATE TABLE Clinic_Account (
    id SERIAL PRIMARY KEY,
    account_id INTEGER NOT NULL,
    clinic_id INTEGER NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Account(id),
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id)
);

CREATE TABLE Account_Contact (
    id SERIAL PRIMARY KEY,
    account_id INTEGER NOT NULL,
    contact_id INTEGER NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Account(id),
    FOREIGN KEY (contact_id) REFERENCES Contact(id)
);

CREATE TABLE Owner (
    id SERIAL PRIMARY KEY,
    clinic_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id),
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE Client_Pet (
    id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    pet_id INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (pet_id) REFERENCES Pet(id)
);

CREATE TABLE MedicalRecord_Medication (
    id SERIAL PRIMARY KEY,
    quantity INTEGER NOT NULL,
    medical_record_id INTEGER NOT NULL,
    medication_id INTEGER NOT NULL,
    FOREIGN KEY (medical_record_id) REFERENCES MedicalRecord(id),
    FOREIGN KEY (medication_id) REFERENCES Medication(id)
);

CREATE TABLE Appointment_Services (
    id SERIAL PRIMARY KEY,
    appointment_id INTEGER NOT NULL,
    services_id INTEGER NOT NULL,
    FOREIGN KEY (appointment_id) REFERENCES Appointment(id),
    FOREIGN KEY (services_id) REFERENCES Services(id)
);
