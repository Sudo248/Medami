const db = require('../config/db')

class Account{
    constructor(emailOrPhone, userId, password){
        this.emailOrPhone = emailOrPhone;
        this.userId = userId;
        this.password = password;
    }

    insert(){
        let sql = `
            INSERT INTO account (
                emailOrPhone,
                userId,
                password
            )
            VALUES(
                '${this.emailOrPhone}',
                '${this.userId}',
                '${this.password}'
            );
        `;
        return db.execute(sql);
    }email

    static getAllAccount(){
        let sql = 'SELECT * FROM account;';
        return db.execute(sql);
    }

    static getAccountByEmailOrPhone(emailOrPhone){
        let sql = 'SELECT * FROM account WHERE emailOrPhone = ${emailOrPhone}';
        return db.execute(sql);
    }

}

module.exports = Account;