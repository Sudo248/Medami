const db = require('../config/db');

module.exports = class JunctionPrescriptionMedicine{
    constructor(
        prescription_id, 
        medicine_id
    ){
        this.medicine_id = medicine_id;
        this.prescription_id = prescription_id;
    }

    insert(){
        const sql = `
            INSERT INTO junction_prescription_medicine
            VALUES(
                ${this.prescription_id},
                ${this.medicine_id}
            );
        `;
        return db.execute(sql);
    }

    updateByPrescriptionId(){
        const sql = `
            UPDATE junction_prescription_medicine
            SET 
                medicine_id = ${this.medicine_id}               
            WHERE prescription_id = ${this.prescription_id};
        `;
        return db.execute(sql);
    }

    updateByMedicineId(){
        const sql = `
            UPDATE junction_prescription_medicine
            SET 
            prescription_id = ${this.prescription_id}            
            WHERE medicine_id = ${this.medicine_id};
        `;
        return db.execute(sql);
    }

    static deleteByPrescriptionId(prescription_id){
        const sql = `
            DELETE FROM junction_prescription_medicine
            WHERE prescription_id = ${prescription_id};
        `;

        return db.execute(sql);
    }

    static deleteByMedicineId(medicine_id){
        const sql = `
            DELETE FROM junction_prescription_medicine
            WHERE medicine_id = ${medicine_id};
        `;

        return db.execute(sql);
    }

    static getAllJunctionPM(){
        const sql = 'SELECT * FROM junction_prescription_medicine'
        return db.execute(sql);
    }

    static getJunctionByPrescriptionId(prescription_id){
        const sql = `
            SELECT * FROM junction_prescription_medicine
            WHERE prescription_id = ${prescription_id};
        `;
        return db.execute(sql);
    }

    static getJunctionByMedicineId(medicine_id){
        const sql = `
            SELECT * FROM junction_prescription_medicine
            WHERE medicine_id = ${medicine_id};
        `;
        return db.execute(sql);
    }

    

}