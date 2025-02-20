-- The script initializes the database structure for the vet application.
-- It also populates the database with some default data.

-- todo: change the varchar length to appropriate sizes

-- Create tables.
CREATE TABLE Contact (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    val VARCHAR(255) NOT NULL,
    is_primary BOOLEAN NOT NULL
);

CREATE TABLE Pet (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    species VARCHAR(255) NOT NULL,
    breed VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    weight_kg DECIMAL(10, 2) NOT NULL,
    color VARCHAR(255) NOT NULL,
    sterilized BOOLEAN NOT NULL,
    pet_picture_url VARCHAR(255) NOT NULL,
    microchip_number VARCHAR(255) NOT NULL,
    medical_notes VARCHAR(255) NOT NULL
);

CREATE TABLE Account (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    role VARCHAR(6) NOT NULL, -- vet, client
    profile_picture_url VARCHAR(255) NOT NULL,
    is_verified BOOLEAN NOT NULL
);

CREATE TABLE OpeningHours (
    id SERIAL PRIMARY KEY,
    monday_open_from TIME NULL,
    monday_open_to TIME NULL,
    tuesday_open_from TIME NULL,
    tuesday_open_to TIME NULL,
    wednesday_open_from TIME NULL,
    wednesday_open_to TIME NULL,
    thursday_open_from TIME NULL,
    thursday_open_to TIME NULL,
    friday_open_from TIME NULL,
    friday_open_to TIME NULL,
    saturday_open_from TIME NULL,
    saturday_open_to TIME NULL,
    sunday_open_from TIME NULL,
    sunday_open_to TIME NULL
);

CREATE TABLE Clinic (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    opening_hours_id INTEGER NOT NULL,
    FOREIGN KEY (opening_hours_id) REFERENCES OpeningHours(id)
);

CREATE TABLE Medication (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    dose VARCHAR(255) NOT NULL,
    quantity_in_stock INTEGER NOT NULL,
    expiration_date DATE NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    is_available BOOLEAN NOT NULL,
    clinic_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id)
);

CREATE TABLE Clinic_Contact (
    id SERIAL PRIMARY KEY,
    clinic_id INTEGER NOT NULL,
    contact_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id),
    FOREIGN KEY (contact_id) REFERENCES Contact(id)
);

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

CREATE TABLE Clinic_Account (
    id SERIAL PRIMARY KEY,
    clinic_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id),
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

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

CREATE TABLE Client_Pet (
    id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    pet_id INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (pet_id) REFERENCES Pet(id)
);

CREATE TABLE Invoice (
    id SERIAL PRIMARY KEY,
    invoice_date DATE NOT NULL,
    due_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    amount_paid DECIMAL(10, 2) NOT NULL,
    outstanding_amount DECIMAL(10, 2) NOT NULL,
    payment_status VARCHAR(255) NOT NULL,
    payment_method VARCHAR(255) NOT NULL,
    notes VARCHAR(255) NOT NULL
);

CREATE TABLE Service (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    category VARCHAR(255) NOT NULL,
    duration_minutes INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    is_available BOOLEAN
);

CREATE TABLE Service_Clinic (
    id SERIAL PRIMARY KEY,
    service_id INTEGER NOT NULL,
    clinic_id INTEGER NOT NULL,
    FOREIGN KEY (service_id) REFERENCES Service(id),
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id)
);

CREATE TABLE MedicalRecord (
    id SERIAL PRIMARY KEY,
    diagnosis VARCHAR(255) NOT NULL,
    treatment VARCHAR(255) NOT NULL,
    procedures VARCHAR(255) NOT NULL,
    next_appointment DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    notes VARCHAR(255) NOT NULL
);

CREATE TABLE MedicalRecord_Medication (
    id SERIAL PRIMARY KEY,
    medical_record_id INTEGER NOT NULL,
    medication_id INTEGER NOT NULL,
    FOREIGN KEY (medical_record_id) REFERENCES MedicalRecord(id),
    FOREIGN KEY (medication_id) REFERENCES Medication(id)
);

CREATE TABLE Appointment (
    id SERIAL PRIMARY KEY,
    date_and_hour TIMESTAMP NOT NULL,
    type VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    reason VARCHAR(255) NOT NULL,
    notes VARCHAR(255) NOT NULL,
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

CREATE TABLE Appointment_Service (
    id SERIAL PRIMARY KEY,
    appointment_id INTEGER NOT NULL,
    service_id INTEGER NOT NULL,
    FOREIGN KEY (appointment_id) REFERENCES Appointment(id),
    FOREIGN KEY (service_id) REFERENCES Service(id)
);
