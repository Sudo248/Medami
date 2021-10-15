const db = require('../config/db');

module.exports = class Notification{
    constructor(
        notification_id,
        medicine_id,
        activity_id,
        date_time,
        note
    ){
        this.notification_id = notification_id;
        this.medicine_id = medicine_id;
        this.activity_id = activity_id;
        this.date_time = date_time;
        this.note = note;
    }

    insert(){
        const sql = `
            INSERT INTO notifications
            VALUES(
                ${null},
                ${this.medicine_id},
                ${this.activity_id},
                '${this.date_time}',
                '${this.note}'
            );
        `;
        return db.execute(sql);
    }

    update(){
        const sql = `
            UPDATE notifications
            SET 
                medicine_id = ${this.medicine_id},
                activity_id = ${this.activity_id},
                date_time = '${this.date_time}',
                note = '${this.note}'
            WHERE notification_id = ${this.notification_id};
        `;
        return db.execute(sql);
    }

    static deleteNotificationById(id){
        const sql = `
            DELETE FROM notifications
            WHERE notification_id = ${id};
        `;

        return db.execute(sql);
    }

    static getAllNotifications(){
        const sql = 'SELECT * FROM notifications'
        return db.execute(sql);
    }

    static getNotificationById(id){
        const sql = `
            SELECT * FROM notifications
            WHERE notification_id = ${id} LIMIT 1;
        `;

        return db.execute(sql);
    }

    static getNotificationByMedicineId(medicine_id){
        const sql = `
            SELECT * FROM notifications
            WHERE medicine_id = ${medicine_id};
        `;
        return db.execute(sql);
    }

    static getNotificationByActivityId(activity_id){
        const sql = `
            SELECT * FROM notifications
            WHERE activity_id = ${activity_id};
        `;
        return db.execute(sql);
    }

}