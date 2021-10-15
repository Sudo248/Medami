const db = require('../config/db')

module.exports = class Account{
    constructor(phone_number, user_id, user_name, password){
        this.phone_number = phone_number;
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
    }

    insert(){
        const sql = `
            INSERT INTO accounts
            VALUES(
                '${this.phone_number}',
                ${this.user_id},
                '${this.user_name}',
                '${this.password}'
            );
        `;
        return db.execute(sql);
    }

    update(){
        const sql = `
            UPDATE accounts
                SET 
                    user_id = '${this.user_id}',
                    user_name = '${this.user_name}',
                    password = '${this.password}'
                WHERE phone_number = '${this.phone_number}';

        `;
        db.execute(sql);
    }

    static insertWithPhoneNumber(phone_number){
        const sql = `
            INSERT INTO accounts (phone_number)
            VALUES ('${phone_number}');
        `;
        return db.execute(sql);
    }

    static getAllAccount(){
        const sql = 'SELECT * FROM accounts;';
        return db.execute(sql);
    }

    static getAccountById(id){
        const sql = `
            SELECT * FROM accounts
            WHERE account_id = ${id} LIMIT 1;
        `;
        db.execute(sql);
    }

    static getAccountByPhoneNumber(phone_number){
        const sql = `SELECT * FROM accounts 
                    WHERE phone_number = '${phone_number}' LIMIT 1;
        `;
        return db.execute(sql);
    }

    static getAccountByUserName(user_name){
        const sql = `SELECT * FROM accounts
                    WHERE user_name = '${user_name}' LIMIT 1;
        `; 
        return db.execute(sql);
    }

}

// module.exports = Account;