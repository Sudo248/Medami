const db = require('../config/db');

exports.createDatabase = function(){
    createAccountDB();
}


function createAccountDB() {
    let sql = `
    CREATE TABLE 
    account (
        emailOrPhone VARCHAR(50) PRIMARY KEY,
        userId INT,
        password VARCHAR(20) 
    );
    `;
    return db.excute(sql);
}

function createUserDB(){
    let sql = `
    CREATE TABLE 
    user (
        user_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
        
    );
    `;
}
