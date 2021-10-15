const db = require('../config/db');

module.exports = class Medicine{
    constructor(
        medicine_id,
        name,
        weekly_frequency,
        time_take_pill,
        times_a_day,
        amount_one_time,
        remaining,
        unit,
        from_date,
        to_date
    ){
        this.medicine_id = medicine_id;
        this.name = name;
        this.weekly_frequency = weekly_frequency;
        this.time_take_pill = time_take_pill;
        this.times_a_day = times_a_day;
        this.amount_one_time = amount_one_time;
        this.remaining = remaining;
        this.unit = unit;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    insert(){
        const sql = `
            INSERT INTO medicines
            VALUES(
                ${null},
                '${this.name}',
                ${this.weekly_frequency},
                '${this.time_take_pill}',
                ${this.times_a_day},
                ${this.amount_one_time},
                ${this.remaining},
                '${this.unit}',
                '${this.from_date}',
                '${this.to_date}'
            );
        `;
        return db.execute(sql);
    }

    update(){
        const sql = `
            UPDATE medicines
            SET
                name = '${this.name}',
                weekly_frequency = ${this.weekly_frequency},
                time_take_pill = '${this.time_take_pill}',
                times_a_day = ${this.times_a_day},
                amount_one_time = ${this.amount_one_time},
                remaining = ${this.remaining},
                unit = '${this.unit}',
                from_date = '${this.from_date}',
                to_date = '${this.to_date}'
            WHERE medicine_id = ${this.medicine_id};
        `;
        return db.execute(sql);
    }

    static deleteMedicineById(id){
        const sql = `
            DELETE FROM medicines
            WHERE medicine_id = ${id};
        `;
        return db.execute(sql);
    }

    static getAllMedicine(){
        const sql = 'SELECT * FROM medicines';
        return db.execute(sql);
    }

    static getMedicineById(id){
        const sql = `
            SELECT * FROM medicines
            WHERE medicine_id = ${id} LIMIT 1;
        `;
        return db.execute(sql);
    }

    static getMedicineIsUsing(){
        const current_date = db.execute('SELECT CURDATE()');
        const sql = `
            SELECT * FROM medicines
            WHERE to_date <= ${current_date}; 
        `;
        return db.execute(sql);
    }

}   