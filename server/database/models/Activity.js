const db = require('../config/db');

module.exports = class Activity{
    constructor(
        activity_id ,
        user_id ,
        name ,
        note 
    ){
        this.activity_id = activity_id;
        this.user_id = user_id;
        this.name = name;
        this.note = note;
    }

    insert(){

        const sql = `
            INSERT INTO activities
            VALUES(
                ${null},
                ${this.user_id},
                '${this.name}',
                '${this.note}'
            );
        `;

        return db.execute(sql);

    }

    update(){

        const sql = `
            UPDATE activities
            SET 
                user_id = ${this.user_id},
                name = '${this.name}',
                note = '${this.note}'
            WHERE activity_id = ${this.activity_id};
        `;

        return db.execute(sql);
    }

    static deleteActivityById(id){

        const sql = `
            DELETE FROM activities
            WHERE activity_id = ${id};
        `;

        return db.execute(sql);
    }

    static getAllActivities(){
        const sql = 'SELECT * FROM activities'
        return db.execute(sql);
    }

    static getActivityById(id){
        const sql = `
            SELECT * FROM activities
            WHERE activity_id = ${id} LIMIT 1;
        `;

        return db.execute(sql);
    }

    static getActivitiesByUserId(user_id){
        const sql = `
            SELECT * FROM activities
            WHERE user_id = ${user_id};
        `;
        return db.execute(sql);
    }


}