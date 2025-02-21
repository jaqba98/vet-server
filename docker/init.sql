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
    status VARCHAR(11) NOT NULL, --scheduled, in_progress, completed, cancelled, no_show, rescheduled
    notes TEXT NOT NULL
);

-- todo: I am here
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

-- ## Create tables with foreign keys. ##
CREATE TABLE Clinic (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    opening_hours_id INTEGER NOT NULL,
    FOREIGN KEY (opening_hours_id) REFERENCES OpeningHours(id)
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

-- todo: I am here
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

CREATE TABLE Account_Contact (
    id SERIAL PRIMARY KEY,
    account_id INTEGER NOT NULL,
    contact_id INTEGER NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Account(id),
    FOREIGN KEY (contact_id) REFERENCES Contact(id)
);

CREATE TABLE Owner (
    id SERIAL PRIMARY KEY,
    account_id INTEGER NOT NULL,
    clinic_id INTEGER NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Account(id),
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id)
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

-- todo: I am here

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
        '09:00:00', '17:00:00',
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
    ),
    -- id = 6, Clinic id = 1, Vet id = 1
    (
        '08:00:00', '12:00:00',
        '08:00:00', '12:00:00',
        '12:00:00', '16:00:00',
        NULL, NULL,
        NULL, NULL,
        '08:00:00', '12:00:00',
        NULL, NULL
    ),
    -- id = 7, Clinic id = 1, Vet id = 2
    (
        '12:00:00', '16:00:00',
        '12:00:00', '16:00:00',
        NULL, NULL,
        '08:00:00', '12:00:00',
        '12:00:00', '16:00:00',
        NULL, NULL,
        NULL, NULL
    ),
    -- id = 8, Clinic id = 1, Vet id = 3
    (
        NULL, NULL,
        NULL, NULL,
        '08:00:00', '12:00:00',
        '12:00:00', '16:00:00',
        '08:00:00', '12:00:00',
        NULL, NULL,
        NULL, NULL
    ),
    -- id = 9, Clinic id = 2, Vet id = 4
    (
        '09:00:00', '13:00:00',
        NULL, NULL,
        '09:00:00', '13:00:00',
        '13:00:00', '17:00:00',
        NULL, NULL,
        '10:00:00', '14:00:00',
        NULL, NULL
    ),
    -- id = 10, Clinic id = 2, Vet id = 5
    (
        '13:00:00', '17:00:00',
        '09:00:00', '13:00:00',
        NULL, NULL,
        '09:00:00', '13:00:00',
        '13:00:00', '17:00:00',
        NULL, NULL,
        NULL, NULL
    ),
    -- id = 11, Clinic id = 2, Vet id = 6
    (
        NULL, NULL,
        '13:00:00', '17:00:00',
        '13:00:00', '17:00:00',
        NULL, NULL,
        '09:00:00', '13:00:00',
        NULL, NULL,
        NULL, NULL
    ),
    -- id = 12, Clinic id = 3, Vet id = 7
    (
        '12:00:00', '16:00:00',
        NULL, NULL,
        '12:00:00', '16:00:00',
        '12:00:00', '16:00:00',
        NULL, NULL,
        NULL, NULL,
        NULL, NULL
    ),
    -- id = 13, Clinic id = 3, Vet id = 8
    (
        '16:00:00', '20:00:00',
        '12:00:00', '16:00:00',
        NULL, NULL,
        '16:00:00', '20:00:00',
        NULL, NULL,
        '12:00:00', '15:00:00',
        NULL, NULL
    ),
    -- id = 14, Clinic id = 3, Vet id = 9
    (
        NULL, NULL,
        '16:00:00', '20:00:00',
        '16:00:00', '20:00:00',
        NULL, NULL,
        NULL, NULL,
        '15:00:00', '18:00:00',
        NULL, NULL
    ),
    -- id = 15, Clinic id = 4, Vet id = 10
    (
        '08:00:00', '13:00:00',
        '08:00:00', '13:00:00',
        '08:00:00', '13:00:00',
        NULL, NULL,
        NULL, NULL,
        '08:00:00', '14:00:00',
        NULL, NULL
    ),
    -- id = 16, Clinic id = 4, Vet id = 11
    (
        '13:00:00', '18:00:00',
        '13:00:00', '18:00:00',
        '13:00:00', '18:00:00',
        '13:00:00', '18:00:00',
        '13:00:00', '18:00:00',
        NULL, NULL,
        NULL, NULL
    ),
    -- id = 17, Clinic id = 4, Vet id = 12
    (
        NULL, NULL,
        NULL, NULL,
        NULL, NULL,
        '08:00:00', '13:00:00',
        '08:00:00', '13:00:00',
        NULL, NULL,
        '10:00:00', '14:00:00'
    ),
    -- id = 18, Clinic id = 5, Vet id = 13
    (
        '07:30:00', '13:45:00',
        '07:30:00', '13:45:00',
        '07:30:00', '13:45:00',
        NULL, NULL,
        NULL, NULL,
        '09:00:00', '14:00:00',
        NULL, NULL
    ),
    -- id = 19, Clinic id = 5, Vet id = 14
    (
        NULL, NULL,
        NULL, NULL,
        NULL, NULL,
        '13:45:00', '19:00:00',
        '13:45:00', '19:00:00',
        NULL, NULL,
        NULL, NULL
    ),
    -- id = 20, Clinic id = 5, Vet id = 15
    (
        '13:45:00', '19:00:00',
        '13:45:00', '19:00:00',
        '13:45:00', '19:00:00',
        '07:30:00', '13:45:00',
        '07:30:00', '13:45:00',
        NULL, NULL,
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

INSERT INTO Pet (name, species, breed, date_of_birth, weight_kg, color, sterilized, picture_url, microchip_number, medical_notes) VALUES
    -- id = 1
    ('Bella', 'Dog', 'Labrador Retriever', '2019-04-15', 30.50, 'Yellow', TRUE, 'https://example.com/bella.jpg', '1234567890', 'No known medical issues'),
    -- id = 2
    ('Max', 'Dog', 'German Shepherd', '2020-02-25', 40.00, 'Black and Tan', FALSE, 'https://example.com/max.jpg', '9876543210', 'Has mild hip dysplasia'),
    -- id = 3
    ('Luna', 'Cat', 'Siamese', '2022-06-10', 4.20, 'Cream with Blue Eyes', TRUE, 'https://example.com/luna.jpg', '5647382910', 'No medical concerns'),
    -- id = 4
    ('Charlie', 'Dog', 'Golden Retriever', '2021-08-14', 28.00, 'Golden', TRUE, 'https://example.com/charlie.jpg', '6789054321', 'Vaccinated, healthy'),
    -- id = 5
    ('Whiskers', 'Cat', 'Persian', '2018-09-20', 5.50, 'White', FALSE, 'https://example.com/whiskers.jpg', NULL, 'Regular grooming required'),
    -- id = 6
    ('Rocky', 'Dog', 'Bulldog', '2017-11-02', 15.00, 'Brindle', TRUE, 'https://example.com/rocky.jpg', NULL, 'Chronic skin allergies'),
    -- id = 7
    ('Zoe', 'Rabbit', 'Holland Lop', '2023-01-18', 1.80, 'Brown and White', FALSE, 'https://example.com/zoe.jpg', NULL, 'Healthy and active'),
    -- id = 8
    ('Simba', 'Cat', 'Maine Coon', '2016-04-30', 8.00, 'Tabby', TRUE, 'https://example.com/simba.jpg', NULL, 'No special medical notes'),
    -- id = 9
    ('Daisy', 'Dog', 'Beagle', '2018-10-05', 9.00, 'Tri-color', TRUE, 'https://example.com/daisy.jpg', '3344556677', 'Occasional ear infections'),
    -- id = 10
    ('Buddy', 'Dog', 'Poodle', '2015-12-22', 12.50, 'White', TRUE, 'https://example.com/buddy.jpg', '2233667788', 'Needs regular teeth cleaning'),
    -- id = 11
    ('Chester', 'Hamster', 'Syrian', '2023-05-06', 0.20, 'Golden', FALSE, 'https://example.com/chester.jpg', NULL, 'No medical concerns'),
    -- id = 12
    ('Milo', 'Cat', 'Ragdoll', '2020-11-17', 6.80, 'Blue Bicolor', TRUE, 'https://example.com/milo.jpg', NULL, 'Occasional hairballs'),
    -- id = 13
    ('Misty', 'Dog', 'Pit Bull', NULL, 22.00, 'Gray', NULL, 'https://example.com/misty.jpg', NULL, 'Found stray, no known medical history'),
    -- id = 14
    ('Tiger', 'Cat', 'Domestic Shorthair', NULL, NULL, 'Orange', NULL, 'https://example.com/tiger.jpg', NULL, 'No known owner, may have been abandoned'),
    -- id = 15
    ('Benny', 'Dog', 'Beagle', NULL, 12.00, 'Brown and White', NULL, 'https://example.com/benny.jpg', NULL, 'Found as a puppy, history unknown');

INSERT INTO MedicalRecord (diagnosis, treatment, procedures, next_appointment, status, notes) VALUES
    -- id = 1
    ('Ear Infection', 'Antibiotic drops', 'Ear cleaning', '2025-02-15', 'completed', 'Mild infection, prescribed drops for 7 days.'),
    -- id = 2
    ('Fractured Leg', 'Splint and painkillers', 'X-ray, cast placement', '2025-02-10', 'in_progress', 'Follow-up needed in 2 weeks for cast check.'),
    -- id = 3
    ('Obesity', 'Dietary adjustments', 'Weight check', '2025-04-01', 'scheduled', 'Recommended special diet and exercise.'),
    -- id = 4
    ('Dental Disease', 'Tooth extraction', 'Dental cleaning, extraction', '2025-02-05', 'completed', 'One tooth removed, antibiotics prescribed.'),
    -- id = 5
    ('Skin Allergy', 'Antihistamines', 'Skin test', '2025-02-18', 'scheduled', 'Allergy suspected, testing in progress.'),
    -- id = 6
    ('Arthritis', 'Pain management', 'Joint mobility test', '2025-02-21', 'in_progress', 'Started joint supplements, monitoring mobility.'),
    -- id = 7
    ('Parvovirus', 'IV Fluids and antiviral', 'Blood test, isolation', '2025-02-21', 'in_progress', 'Critical condition, under intensive care.'),
    -- id = 8
    ('Eye Infection', 'Antibiotic ointment', 'Eye exam', '2025-01-28', 'completed', 'Mild conjunctivitis, treatment successful.'),
    -- id = 9
    ('Broken Tail', 'Bandaging and rest', 'X-ray, splinting', '2025-03-20', 'scheduled', 'Minor fracture, should heal in 4 weeks.'),
    -- id = 10
    ('Heart Murmur', 'Medication and monitoring', 'Cardiac ultrasound', '2025-03-25', 'scheduled', 'Monitoring required, mild heart murmur.'),
    -- id = 11
    ('Kennel Cough', 'Cough suppressant', 'Lung auscultation', '2025-03-01', 'rescheduled', 'Mild symptoms, postponed follow-up visit.'),
    -- id = 12
    ('Tick Infestation', 'Tick removal and prevention', 'Tick check', '2025-02-10', 'completed', 'All ticks removed, prescribed preventive treatment.'),
    -- id = 13
    ('Gastroenteritis', 'IV Fluids and antibiotics', 'Stool test', '2025-02-25', 'in_progress', 'Dehydration improving, monitoring stool.'),
    -- id = 14
    ('Hip Dysplasia', 'Physiotherapy and supplements', 'X-ray', '2025-03-28', 'scheduled', 'Mild case, advised weight control and therapy.'),
    -- id = 15
    ('Ear Mites', 'Topical medication', 'Ear swab', '2025-02-10', 'no_show', 'Client did not show up, needs rescheduling.');

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

INSERT INTO Vet (salary, license_number, license_issue_date, license_expiry_date, specializations, years_of_experience, account_id, opening_hours_id) VALUES
    -- id = 1
    (60000.00, 'VET123456', '2015-06-15', '2025-06-15', 'Small Animals', 10, 1, 6),
    -- id = 2
    (75000.00, 'VET654321', '2012-09-20', '2027-09-20', 'Exotic Animals', 15, 2, 7),
    -- id = 3
    (58000.00, 'VET987654', '2018-03-10', '2026-03-10', 'Equine Medicine', 8, 3, 8),
    -- id = 4
    (72000.00, 'VET111222', '2010-12-05', '2025-12-05', 'Surgery', 18, 7, 9),
    -- id = 5
    (64000.00, 'VET333444', '2016-07-22', '2026-07-22', 'Dermatology', 9, 8, 10),
    -- id = 6
    (68000.00, 'VET555666', '2014-05-30', '2024-05-30', 'Ophthalmology', 12, 9, 11),
    -- id = 7
    (70000.00, 'VET777888', '2011-10-14', '2026-10-14', 'Neurology', 14, 13, 12),
    -- id = 8
    (62000.00, 'VET999000', '2017-08-18', '2027-08-18', 'Cardiology', 7, 14, 13),
    -- id = 9
    (59000.00, 'VET121314', '2019-02-25', '2029-02-25', 'Oncology', 5, 15, 14),
    -- id = 10
    (76000.00, 'VET151617', '2009-11-11', '2024-11-11', 'Internal Medicine', 20, 19, 15),
    -- id = 11
    (63000.00, 'VET181920', '2013-04-07', '2028-04-07', 'Anesthesiology', 11, 20, 16),
    -- id = 12
    (67000.00, 'VET212223', '2015-01-29', '2025-01-29', 'Radiology', 13, 21, 17),
    -- id = 13
    (61000.00, 'VET242526', '2018-06-12', '2028-06-12', 'Emergency Medicine', 6, 25, 18),
    -- id = 14
    (75000.00, 'VET272829', '2010-09-19', '2025-09-19', 'Orthopedics', 17, 26, 19),
    -- id = 15
    (73000.00, 'VET303132', '2012-12-31', '2027-12-31', 'Rehabilitation', 16, 27, 20);

INSERT INTO Client (account_id) VALUES
    -- id = 1
    (4),
    -- id = 2
    (5),
    -- id = 3
    (6),
    -- id = 4
    (10),
    -- id = 5
    (11),
    -- id = 6
    (12),
    -- id = 7
    (16),
    -- id = 8
    (17),
    -- id = 9
    (18),
    -- id = 10
    (22),
    -- id = 11
    (23),
    -- id = 12
    (24),
    -- id = 13
    (28),
    -- id = 14
    (29),
    -- id = 15
    (30);

INSERT INTO Medication (name, description, manufacturer, dose, quantity_in_stock, expiration_date, price, is_available, clinic_id) VALUES
    -- id = 1
    ('Carprofen', 'NSAID for pain relief in dogs.', 'Zoetis', '25 mg per 10 kg, once daily', 50, '2026-05-12', 12.50, TRUE, 1),
    -- id = 2
    ('Amoxicillin', 'Broad-spectrum antibiotic.', 'Boehringer Ingelheim', '10 mg per kg, twice daily', 100, '2025-11-30', 8.99, TRUE, 1),
    -- id = 3
    ('Furosemide', 'Diuretic for fluid retention.', 'Elanco', '2 mg per kg, twice daily', 75, '2026-08-20', 6.75, TRUE, 1),
    -- id = 4
    ('Prednisolone', 'Corticosteroid for inflammation.', 'Dechra', '0.5 mg per kg, once daily', 60, '2025-07-15', 9.50, TRUE, 1),
    -- id = 5
    ('Metronidazole', 'Antibiotic for GI infections.', 'Virbac', '15 mg per kg, twice daily', 80, '2026-04-05', 7.25, TRUE, 1),
    -- id = 6
    ('Meloxicam', 'NSAID for pain relief.', 'Boehringer Ingelheim', '0.1 mg per kg, once daily', 40, '2025-12-10', 14.99, TRUE, 1),
    -- id = 7
    ('Marbofloxacin', 'Antibiotic for bacterial infections.', 'Vetoquinol', '2 mg per kg, once daily', 30, '2026-03-25', 18.50, TRUE, 1),
    -- id = 8
    ('Ivermectin', 'Antiparasitic treatment.', 'Merial', '0.2 mg per kg, single dose', 90, '2025-09-30', 5.99, TRUE, 1),
    -- id = 9
    ('Cefalexin', 'Antibiotic for skin infections.', 'Norbrook', '25 mg per kg, twice daily', 70, '2026-01-20', 10.25, TRUE, 1),
    -- id = 10
    ('Gabapentin', 'Pain reliever and anticonvulsant.', 'Aurora Pharmaceuticals', '10 mg per kg, twice daily', 45, '2025-10-15', 11.75, TRUE, 1),
    -- id = 11
    ('Tramadol', 'Opioid analgesic.', 'Greenstone', '5 mg per kg, twice daily', 35, '2025-06-10', 13.99, TRUE, 1),
    -- id = 12
    ('Doxycycline', 'Antibiotic for respiratory infections.', 'Pfizer', '5 mg per kg, once daily', 65, '2026-02-18', 9.99, TRUE, 1),
    -- id = 13
    ('Clindamycin', 'Antibiotic for dental infections.', 'Sandoz', '11 mg per kg, twice daily', 55, '2026-06-30', 8.49, TRUE, 1),
    -- id = 14
    ('Tylosin', 'Antibiotic for chronic diarrhea.', 'Huvepharma', '10 mg per kg, twice daily', 25, '2025-08-22', 15.25, TRUE, 1),
    -- id = 15
    ('Levothyroxine', 'Treatment for hypothyroidism.', 'Merck Animal Health', '0.02 mg per kg, twice daily', 50, '2026-07-05', 16.99, TRUE, 1),
    -- id = 16
    ('Enrofloxacin', 'Fluoroquinolone antibiotic.', 'Bayer', '5 mg per kg, once daily', 40, '2025-09-01', 19.99, TRUE, 2),
    -- id = 17
    ('Ketoconazole', 'Antifungal treatment.', 'Hikma', '10 mg per kg, once daily', 30, '2026-03-10', 21.50, TRUE, 2),
    -- id = 18
    ('Apoquel', 'Anti-itch medication.', 'Zoetis', '0.4 mg per kg, once daily', 60, '2026-05-20', 35.99, TRUE, 2),
    -- id = 19
    ('Methimazole', 'Treatment for hyperthyroidism.', 'Lloyd Inc.', '2.5 mg per kg, twice daily', 45, '2025-11-02', 22.75, TRUE, 2),
    -- id = 20
    ('Omeprazole', 'Proton pump inhibitor.', 'Sandoz', '0.5 mg per kg, once daily', 50, '2026-06-14', 12.99, TRUE, 2),
    -- id = 21
    ('Pimobendan', 'Heart failure treatment.', 'Vetmedin', '0.25 mg per kg, twice daily', 35, '2026-07-30', 29.99, TRUE, 2),
    -- id = 22
    ('Hydroxyzine', 'Antihistamine for allergies.', 'Dr. Reddy’s', '2 mg per kg, twice daily', 50, '2025-12-05', 8.50, TRUE, 2),
    -- id = 23
    ('Famotidine', 'Acid reducer.', 'Mylan', '0.5 mg per kg, once daily', 75, '2026-04-25', 5.50, TRUE, 2),
    -- id = 24
    ('Sucralfate', 'Gastroprotective agent.', 'Hikma', '0.25 g per kg, twice daily', 40, '2026-01-10', 14.75, TRUE, 2),
    -- id = 25
    ('Diazepam', 'Anxiety and seizure control.', 'Teva', '0.5 mg per kg, as needed', 20, '2025-10-30', 18.99, TRUE, 2),
    -- id = 26
    ('Buprenorphine', 'Opioid pain reliever.', 'Reckitt Benckiser', '0.02 mg per kg, as needed', 15, '2025-09-15', 39.99, TRUE, 2),
    -- id = 27
    ('Atenolol', 'Beta-blocker for heart conditions.', 'Aurobindo', '0.2 mg per kg, once daily', 55, '2026-03-20', 9.99, TRUE, 2),
    -- id = 28
    ('Spironolactone', 'Diuretic for heart failure.', 'Amneal', '1 mg per kg, once daily', 50, '2026-06-05', 11.50, TRUE, 2),
    -- id = 29
    ('Chlorpheniramine', 'Antihistamine for allergies.', 'Perrigo', '0.5 mg per kg, twice daily', 65, '2025-12-15', 6.99, TRUE, 2),
    -- id = 30
    ('Cisapride', 'Motility agent for GI issues.', 'Compounded', '0.1 mg per kg, twice daily', 30, '2026-05-22', 24.99, TRUE, 2),
    -- id = 31
    ('Phenobarbital', 'Seizure control medication.', 'West-Ward', '2 mg per kg, twice daily', 70, '2025-10-05', 16.75, TRUE, 3),
    -- id = 32
    ('Pentoxifylline', 'Improves blood flow.', 'Teva', '10 mg per kg, twice daily', 50, '2026-07-01', 14.99, TRUE, 3),
    -- id = 33
    ('Selegiline', 'Cognitive dysfunction treatment.', 'Dechra', '0.5 mg per kg, once daily', 40, '2026-02-10', 29.50, TRUE, 3),
    -- id = 34
    ('Toltrazuril', 'Coccidiosis treatment.', 'Bayer', '20 mg per kg, once daily', 35, '2026-08-01', 19.99, TRUE, 3),
    -- id = 35
    ('Alprazolam', 'Anxiety relief.', 'Greenstone', '0.01 mg per kg, as needed', 20, '2025-11-20', 12.75, TRUE, 3),
    -- id = 36
    ('Cefpodoxime', 'Cephalosporin antibiotic.', 'Zydus', '5 mg per kg, once daily', 45, '2026-03-18', 22.99, TRUE, 3),
    -- id = 37
    ('Ranitidine', 'Acid reducer.', 'Sandoz', '2 mg per kg, twice daily', 50, '2026-05-10', 9.50, TRUE, 3),
    -- id = 38
    ('Tulathromycin', 'Macrolide antibiotic for respiratory infections.', 'Zoetis', '2.5 mg per kg, single dose', 45, '2026-06-15', 39.99, TRUE, 3),
    -- id = 39
    ('Tolfenamic Acid', 'NSAID for pain relief and inflammation.', 'Vetoquinol', '4 mg per kg, once daily', 30, '2026-08-10', 25.99, TRUE, 3),
    -- id = 40
    ('Cefovecin', 'Long-acting antibiotic for skin infections.', 'Zoetis', '8 mg per kg, single dose', 50, '2026-05-20', 74.99, TRUE, 3),
    -- id = 41
    ('Robenacoxib', 'NSAID for post-operative pain relief.', 'Elanco', '1 mg per kg, once daily', 40, '2026-09-05', 29.99, TRUE, 3),
    -- id = 42
    ('Selamectin', 'Antiparasitic treatment for fleas and mites.', 'Zoetis', '6 mg per kg, monthly', 60, '2026-07-22', 44.99, TRUE, 3),
    -- id = 43
    ('Chloramphenicol', 'Broad-spectrum antibiotic.', 'Bimeda', '50 mg per kg, twice daily', 30, '2026-06-10', 19.99, TRUE, 3),
    -- id = 44
    ('Ciclosporin', 'Immunosuppressant for atopic dermatitis.', 'Novartis', '5 mg per kg, once daily', 45, '2026-05-30', 89.99, TRUE, 3),
    -- id = 45
    ('Spinosad', 'Oral flea treatment.', 'Elanco', '30 mg per kg, monthly', 55, '2026-08-15', 59.99, TRUE, 3),
    -- id = 46
    ('Lufenuron', 'Insect growth regulator for flea control.', 'Novartis', '10 mg per kg, monthly', 65, '2026-07-01', 34.99, TRUE, 4),
    -- id = 47
    ('Methocarbamol', 'Muscle relaxant for muscle spasms.', 'Teva', '20 mg per kg, three times daily', 35, '2026-06-18', 22.99, TRUE, 4),
    -- id = 48
    ('Prazosin', 'Alpha-blocker for urinary tract issues.', 'Pfizer', '0.5 mg per kg, twice daily', 50, '2026-05-10', 17.99, TRUE, 4),
    -- id = 49
    ('Acepromazine', 'Sedative for anxiety and motion sickness.', 'Boehringer Ingelheim', '0.02 mg per kg, as needed', 40, '2026-07-14', 14.99, TRUE, 4),
    -- id = 50
    ('Hydralazine', 'Vasodilator for heart conditions.', 'Mylan', '2 mg per kg, twice daily', 30, '2026-06-28', 12.99, TRUE, 4),
    -- id = 51
    ('Moxidectin', 'Antiparasitic treatment.', 'Bayer', '0.4 mg per kg, monthly', 60, '2026-09-25', 37.99, TRUE, 4),
    -- id = 52
    ('Chlorpheniramine', 'Antihistamine for allergies.', 'Sandoz', '0.2 mg per kg, twice daily', 70, '2026-05-05', 9.99, TRUE, 4),
    -- id = 53
    ('Ketoprofen', 'NSAID for inflammation and pain.', 'Hikma', '1 mg per kg, once daily', 45, '2026-06-20', 23.99, TRUE, 4),
    -- id = 54
    ('Ranitidine', 'Acid reducer for stomach ulcers.', 'Dr. Reddy’s', '2 mg per kg, twice daily', 55, '2026-07-30', 8.99, TRUE, 4),
    -- id = 55
    ('Misoprostol', 'Gastroprotectant for ulcer prevention.', 'Pfizer', '3 mcg per kg, twice daily', 25, '2026-09-12', 29.99, TRUE, 4),
    -- id = 56
    ('Omeprazole Paste', 'Equine gastric ulcer treatment.', 'Merial', '4 mg per kg, once daily', 35, '2026-06-07', 49.99, TRUE, 4),
    -- id = 57
    ('Neomycin', 'Antibiotic for bacterial infections.', 'Norbrook', '10 mg per kg, twice daily', 50, '2026-08-03', 18.99, TRUE, 4),
    -- id = 58
    ('Methimazole Gel', 'Topical treatment for hyperthyroidism.', 'Lloyd Inc.', '5 mg per ear, once daily', 40, '2026-07-09', 42.99, TRUE, 4),
    -- id = 59
    ('Sotalol', 'Beta-blocker for cardiac arrhythmias.', 'Aurobindo', '1 mg per kg, twice daily', 30, '2026-05-27', 24.99, TRUE, 4),
    -- id = 60
    ('Valacyclovir', 'Antiviral for feline herpesvirus.', 'Teva', '20 mg per kg, twice daily', 35, '2026-06-22', 31.99, TRUE, 4),
    -- id = 61
    ('Pentoxifylline', 'Anti-inflammatory for vascular conditions.', 'Mylan', '15 mg per kg, twice daily', 40, '2026-07-21', 26.99, TRUE, 5),
    -- id = 62
    ('Cromolyn Sodium', 'Mast cell stabilizer for allergic conditions.', 'Sandoz', '1 mg per kg, twice daily', 30, '2026-08-08', 19.99, TRUE, 5),
    -- id = 63
    ('Fipronil', 'Topical flea and tick treatment.', 'Bayer', '0.67 ml per application, monthly', 80, '2026-09-30', 22.99, TRUE, 5),
    -- id = 64
    ('Trilostane', 'Adrenal suppressant for Cushing’s disease.', 'Dechra', '2 mg per kg, once daily', 50, '2026-06-12', 49.99, TRUE, 5),
    -- id = 65
    ('Azathioprine', 'Immunosuppressive therapy.', 'Sandoz', '2 mg per kg, once daily', 30, '2026-07-18', 39.99, TRUE, 5),
    -- id = 66
    ('Levothyroxine', 'Thyroid hormone replacement.', 'Merck', '0.02 mg per kg, once daily', 60, '2026-05-08', 15.99, TRUE, 5),
    -- id = 67
    ('Trazodone', 'Sedative for anxiety and stress.', 'Teva', '5 mg per kg, as needed', 40, '2026-06-25', 18.99, TRUE, 5),
    -- id = 68
    ('Clindamycin', 'Antibiotic for dental and bone infections.', 'Pfizer', '11 mg per kg, twice daily', 70, '2026-08-17', 27.99, TRUE, 5),
    -- id = 69
    ('Amitriptyline', 'Tricyclic antidepressant for behavioral issues.', 'Elanco', '1 mg per kg, once daily', 30, '2026-07-12', 21.99, TRUE, 5),
    -- id = 70
    ('Gabapentin', 'Neuropathic pain reliever and anticonvulsant.', 'Pfizer', '5 mg per kg, twice daily', 50, '2026-09-05', 19.99, TRUE, 5),
    -- id = 71
    ('Maropitant', 'Anti-nausea and vomiting medication.', 'Zoetis', '1 mg per kg, once daily', 40, '2026-06-30', 29.99, TRUE, 5),
    -- id = 72
    ('Pimobendan', 'Heart failure treatment.', 'Boehringer Ingelheim', '0.25 mg per kg, twice daily', 45, '2026-08-01', 55.99, TRUE, 5),
    -- id = 73
    ('Doxycycline', 'Broad-spectrum antibiotic.', 'Pfizer', '10 mg per kg, once daily', 75, '2026-05-20', 23.99, TRUE, 5),
    -- id = 74
    ('Meloxicam', 'NSAID for inflammation and pain.', 'Boehringer Ingelheim', '0.1 mg per kg, once daily', 60, '2026-07-28', 31.99, TRUE, 5),
    -- id = 75
    ('Selegiline', 'Treatment for cognitive dysfunction and Cushing’s disease.', 'Zoetis', '0.5 mg per kg, once daily', 35, '2026-06-15', 42.99, TRUE, 5);

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
    -- id = 2, Clinic id = 1, Account id = 2
    (1, 2),
    -- id = 3, Clinic id = 1, Account id = 3
    (1, 3),
    -- id = 4, Clinic id = 1, Account id = 4
    (1, 4),
    -- id = 5, Clinic id = 1, Account id = 5
    (1, 5),
    -- id = 6, Clinic id = 1, Account id = 6
    (1, 6),
    -- id = 7, Clinic id = 2, Account id = 7
    (2, 7),
    -- id = 8, Clinic id = 2, Account id = 8
    (2, 8),
    -- id = 9, Clinic id = 2, Account id = 9
    (2, 9),
    -- id = 10, Clinic id = 2, Account id = 10
    (2, 10),
    -- id = 11, Clinic id = 2, Account id = 11
    (2, 11),
    -- id = 12, Clinic id = 2, Account id = 12
    (2, 12),
    -- id = 13, Clinic id = 3, Account id = 13
    (3, 13),
    -- id = 14, Clinic id = 3, Account id = 14
    (3, 14),
    -- id = 15, Clinic id = 3, Account id = 15
    (3, 15),
    -- id = 16, Clinic id = 3, Account id = 16
    (3, 16),
    -- id = 17, Clinic id = 3, Account id = 17
    (3, 17),
    -- id = 18, Clinic id = 3, Account id = 18
    (3, 18),
    -- id = 19, Clinic id = 4, Account id = 19
    (4, 19),
    -- id = 20, Clinic id = 4, Account id = 20
    (4, 20),
    -- id = 21, Clinic id = 4, Account id = 21
    (4, 21),
    -- id = 22, Clinic id = 4, Account id = 22
    (4, 22),
    -- id = 23, Clinic id = 4, Account id = 23
    (4, 23),
    -- id = 24, Clinic id = 4, Account id = 24
    (4, 24),
    -- id = 25, Clinic id = 5, Account id = 25
    (5, 25),
    -- id = 26, Clinic id = 5, Account id = 26
    (5, 26),
    -- id = 27, Clinic id = 5, Account id = 27
    (5, 27),
    -- id = 28, Clinic id = 5, Account id = 28
    (5, 28),
    -- id = 29, Clinic id = 5, Account id = 29
    (5, 29),
    -- id = 30, Clinic id = 5, Account id = 30
    (5, 30);

INSERT INTO Account_Contact (account_id, contact_id) VALUES
    -- id = 1, Account id = 1, Contact id = 16
    (1, 16),
    -- id = 2, Account id = 1, Contact id = 17
    (1, 17),
    -- id = 3, Account id = 2, Contact id = 18
    (2, 18),
    -- id = 4, Account id = 2, Contact id = 19
    (2, 19),
    -- id = 5, Account id = 3, Contact id = 20
    (3, 20),
    -- id = 6, Account id = 3, Contact id = 21
    (3, 21),
    -- id = 7, Account id = 4, Contact id = 22
    (4, 22),
    -- id = 8, Account id = 4, Contact id = 23
    (4, 23),
    -- id = 9, Account id = 5, Contact id = 24
    (5, 24),
    -- id = 10, Account id = 5, Contact id = 25
    (5, 25),
    -- id = 11, Account id = 6, Contact id = 26
    (6, 26),
    -- id = 12, Account id = 6, Contact id = 27
    (6, 27),
    -- id = 13, Account id = 7, Contact id = 28
    (7, 28),
    -- id = 14, Account id = 7, Contact id = 29
    (7, 29),
    -- id = 15, Account id = 8, Contact id = 30
    (8, 30),
    -- id = 16, Account id = 8, Contact id = 31
    (8, 31),
    -- id = 17, Account id = 9, Contact id = 32
    (9, 32),
    -- id = 18, Account id = 9, Contact id = 33
    (9, 33),
    -- id = 19, Account id = 10, Contact id = 34
    (10, 34),
    -- id = 20, Account id = 10, Contact id = 35
    (10, 35),
    -- id = 21, Account id = 11, Contact id = 36
    (11, 36),
    -- id = 22, Account id = 11, Contact id = 37
    (11, 37),
    -- id = 23, Account id = 12, Contact id = 38
    (12, 38),
    -- id = 24, Account id = 12, Contact id = 39
    (12, 39),
    -- id = 25, Account id = 13, Contact id = 40
    (13, 40),
    -- id = 26, Account id = 13, Contact id = 41
    (13, 41),
    -- id = 27, Account id = 14, Contact id = 42
    (14, 42),
    -- id = 28, Account id = 14, Contact id = 43
    (14, 43),
    -- id = 29, Account id = 15, Contact id = 44
    (15, 44),
    -- id = 30, Account id = 15, Contact id = 45
    (15, 45),
    -- id = 31, Account id = 16, Contact id = 46
    (16, 46),
    -- id = 32, Account id = 16, Contact id = 47
    (16, 47),
    -- id = 33, Account id = 17, Contact id = 48
    (17, 48),
    -- id = 34, Account id = 17, Contact id = 49
    (17, 49),
    -- id = 35, Account id = 18, Contact id = 50
    (18, 50),
    -- id = 36, Account id = 18, Contact id = 51
    (18, 51),
    -- id = 37, Account id = 19, Contact id = 52
    (19, 52),
    -- id = 38, Account id = 19, Contact id = 53
    (19, 53),
    -- id = 39, Account id = 20, Contact id = 54
    (20, 54),
    -- id = 40, Account id = 20, Contact id = 55
    (20, 55),
    -- id = 41, Account id = 21, Contact id = 56
    (21, 56),
    -- id = 42, Account id = 21, Contact id = 57
    (21, 57),
    -- id = 43, Account id = 22, Contact id = 58
    (22, 58),
    -- id = 44, Account id = 22, Contact id = 59
    (22, 59),
    -- id = 45, Account id = 23, Contact id = 60
    (23, 60),
    -- id = 46, Account id = 23, Contact id = 61
    (23, 61),
    -- id = 47, Account id = 24, Contact id = 62
    (24, 62),
    -- id = 48, Account id = 24, Contact id = 63
    (24, 63),
    -- id = 49, Account id = 25, Contact id = 64
    (25, 64),
    -- id = 50, Account id = 25, Contact id = 65
    (25, 65),
    -- id = 51, Account id = 26, Contact id = 66
    (26, 66),
    -- id = 52, Account id = 26, Contact id = 67
    (26, 67),
    -- id = 53, Account id = 27, Contact id = 68
    (27, 68),
    -- id = 54, Account id = 27, Contact id = 69
    (27, 69),
    -- id = 55, Account id = 28, Contact id = 70
    (28, 70),
    -- id = 56, Account id = 28, Contact id = 71
    (28, 71),
    -- id = 57, Account id = 29, Contact id = 72
    (29, 72),
    -- id = 58, Account id = 29, Contact id = 73
    (29, 73),
    -- id = 59, Account id = 30, Contact id = 74
    (30, 74),
    -- id = 60, Account id = 30, Contact id = 75
    (30, 75);

INSERT INTO Owner (account_id, clinic_id) VALUES
    -- id = 1, Account id = 1, Clinic id = 1
    (1, 1),
    -- id = 2, Account id = 7, Clinic id = 2
    (7, 2),
    -- id = 3, Account id = 13, Clinic id = 3
    (13, 3),
    -- id = 4, Account id = 19, Clinic id = 4
    (19, 4),
    -- id = 5, Account id = 25, Clinic id = 5
    (25, 5);

INSERT INTO Client_Pet (client_id, pet_id) VALUES
    -- id = 1, Client id = 1, Pet id = 1
    (1, 1),
    -- id = 2, Client id = 2, Pet id = 2
    (2, 2),
    -- id = 3, Client id = 3, Pet id = 3
    (3, 3),
    -- id = 4, Client id = 4, Pet id = 4
    (4, 4),
    -- id = 5, Client id = 5, Pet id = 5
    (5, 5),
    -- id = 6, Client id = 6, Pet id = 6
    (6, 6),
    -- id = 7, Client id = 7, Pet id = 7
    (7, 7),
    -- id = 8, Client id = 8, Pet id = 8
    (8, 8),
    -- id = 9, Client id = 9, Pet id = 9
    (9, 9),
    -- id = 10, Client id = 10, Pet id = 10
    (10, 10),
    -- id = 11, Client id = 11, Pet id = 11
    (11, 11),
    -- id = 12, Client id = 12, Pet id = 12
    (12, 12),
    -- id = 13, Client id = 13, Pet id = 13
    (13, 13),
    -- id = 14, Client id = 14, Pet id = 14
    (14, 14),
    -- id = 15, Client id = 15, Pet id = 15
    (15, 15);

INSERT INTO MedicalRecord_Medication (quantity, medical_record_id, medication_id) VALUES
    -- id = 1
    (1, 1, 10),
    -- id = 2
    (1, 2, 60),
    -- id = 3
    (2, 3, 41),
    -- id = 4
    (3, 4, 49),
    -- id = 5
    (2, 5, 35),
    -- id = 6
    (3, 6, 13),
    -- id = 7
    -- id = 7
    (2, 7, 2),
    -- id = 8
    (2, 8, 60),
    -- id = 9
    (2, 9, 47),
    -- id = 10
    (3, 10, 12),
    -- id = 11
    (1, 11, 45),
    -- id = 12
    (1, 12, 34),
    -- id = 13
    (1, 13, 45),
    -- id = 14
    (3, 14, 38),
    -- id = 15
    (1, 15, 48);
