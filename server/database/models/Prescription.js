const db = require('../config/db');

module.exports = class Prescription{
    constructor(
        prescription_id,
        user_id,
        diagnose,
        date ,
        is_using,
        type,
        name_of_doctor ,
        advice_of_doctor
    ){
        this.prescription_id = prescription_id;
        this.user_id = user_id;
        this.diagnose = diagnose;
        this.date = date;
        this.is_using = is_using;
        this.type = type;
        this.name_of_doctor = name_of_doctor;
        this.advice_of_doctor = advice_of_doctor;

    }

    insert(){
        const sql = `
            INSERT INTO prescriptions
            VALUES(
                ${null},
                ${this.user_id},
                '${this.diagnose}',
                '${this.date}',
                ${this.is_using},
                '${this.type}',
                '${this.name_of_doctor}',
                '${this.advice_of_doctor}'
            );
        `;
        return db.execute(sql);
    }

    update(){
        const sql = `
            UPDATE prescriptions
            SET
                user_id = ${this.user_id},
                diagnose = '${this.diagnose}',
                date = '${this.date}',
                is_using = ${this.is_using},
                type = '${this.type}',
                name_of_doctor = '${this.name_of_doctor}',
                advice_of_doctor = '${this.advice_of_doctor}'
            WHERE prescription_id = ${this.prescription_id};
        `;
        return db.execute(sql);
    }

    static deletePrescriptionById(id){
        const sql = `DELETE FROM prescriptions WHERE prescription_id = ${id}`;
        return db.execute(sql);
    }

    static getAllPrescriptions(){
        const sql = 'SELECT * FROM prescriptions;'
        return db.execute(sql);
    }

    static getPrescriptionById(id){
        const sql = `
            SELECT * FROM prescriptions
            WHERE prescription_id = ${id} LIMIT 1;
        `;
        return db.execute(sql);
    }

    static getPrescriptionsByUserId(user_id){
        const sql = `
            SELECT * FROM prescriptions
            WHERE user_id = ${user_id};
        `;
        return db.execute(sql);
    }

    static getPrescriptionsByUserIdAndIsUsing(user_id, is_using){
        const sql = `
            SELECT * FROM prescriptions
            WHERE user_id = ${user_id} AND is_using = TRUE;
        `;
        return db.execute(sql);
    }




}