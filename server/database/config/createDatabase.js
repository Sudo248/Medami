const db = require('./db');


function createAccountsDB() {
    let sql = `
    CREATE TABLE 
    accounts (
        phone_number VARCHAR(20) PRIMARY KEY,
        user_id INT,
        user_name VARCHAR(50),
        password VARCHAR(20),
        FOREIGN KEY(user_id) 
                    REFERENCES user(user_id)
    );
    `;
    return db.excute(sql);
}

function createUsersDB(){
    let sql = `
    CREATE TABLE 
    users (
        user_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        first_name VARCHAR(50),
        last_name VARCHAR(50) NOT NULL,
        phone_number VARCHAR(20) NOT NULL,
        date_of_birth DATE,
        weight INT(4),
        height INT(4)
    );
    `;
    return db.excute(sql);
}

function createPrescriptionDB(){
    const sql = `
    CREATE TABLE 
    prescriptions(
        prescription_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        user_id INT UNSIGNED ,
        diagnose VARCHAR(255),
        date DATE,
        is_using BOOLEAN,
        type VARCHAR(100),
        name_of_doctor VARCHAR(255),
        advice_of_doctor VARCHAR(255),   
        FOREIGN KEY (user_id) REFERENCES user(user_id)            
    );
    `;
    return db.execute(sql);
}

function createMedamiDB(){
    const sql = `
    CREATE TABLE 
    medicines(
        medicine_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255),
        weekly_frequency INT(2),
        time_take_pill ENUM('truoc an','trong an','sau an'),
        times_a_day INT(5),
        amount_one_time INT(5),
        remaining INT(5),
        unit VARCHAR(50),
        from_date DATE,
        to_date DATE
    );
    `;
    return db.execute(sql);
}

function createActivityDB(){
    const sql = `
        CREATE TABLE 
        activities(
            activity_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
            user_id INT UNSIGNED,
            name VARCHAR(100),
            note VARCHAR(255),
            FOREIGN KEY (user_id) REFERENCES user(user_id)
        );
    `;
    return db.execute(sql);
}

function createNotificationsDB(){
    const sql = `
        CREATE TABLE 
        notifications(
            notification_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
            medicine_id INT UNSIGNED ,
            activity_id INT UNSIGNED,
            date_time DATETIME,
            note VARCHAR(255),
            FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id),
            FOREIGN KEY (activity_id) REFERENCES activities(activity_id)
        );
    `;
    return db.execute(sql);
}

function createJunctionPrescriptionMedicineDB(){
    const sql = `
        CREATE TABLE 
        junction_prescription_medicine(
            prescription_id INT UNSIGNED,
            medicine_id INT UNSIGNED ,
            FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id),
            FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id)
        );
    `;
    return db.execute(sql);
}

module.exports.createDatabase = function(){
    createAccountsDB();
    createUsersDB();
    createPrescriptionDB();
    createMedamiDB();
    createActivityDB();
    createNotificationsDB();
    createJunctionPrescriptionMedicineDB()
}