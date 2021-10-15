const db = require('../config/db');
module.exports = class User{

    constructor(
        user_id, 
        first_name, 
        last_name, 
        phone_number, 
        date_of_birth, 
        weight, 
        height
    ){
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.date_of_birth = date_of_birth;
        this.weight = weight;
        this.height = height;
    }


    insert(){
        const sql = `
            INSERT INTO users 
            VALUES (
                ${null},
                '${this.first_name}',
                '${this.last_name}',
                '${this.phone_number}',
                '${this.date_of_birth}',
                ${this.weight},
                ${this.height}
            );
        `;
        return db.execute(sql);
    }

    update(){
        const sql = `
            UPDATE users 
            SET 
                first_name = '${this.first_name}',
                last_name = '${this.last_name}', 
                phone_number = '${this.phone_number}', 
                date_of_birth = '${this.date_of_birth}', 
                weight = ${this.weight}, 
                height = ${this.height}
            WHERE user_id = ${this.user_id};
        `;
        db.execute(sql);
    }

    static getAllUsers(){
        const sql = 'SELECT * FROM users;';
        return db.execute(sql);
    }

    static getUserById(id){
        const sql = `
            SELECT * FROM users
            WHERE user_id = ${id} LIMIT 1;
        `;
        return db.execute(sql);
    }

    

}
