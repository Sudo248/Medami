const { assert } = require('console');
const Account = require('../database/models/Account');

exports.postAccount = async (req, res, next) => {
    try{
        let {
            phone_number, 
            user_id,
            user_name,
            password
        } = req.body;

        let account = new Account(phone_number, user_id, user_name, password);
        await account.insert();
        res.status(201).json({message:"Insert Account Success!"});
        
    }catch(error){
        next(error);
    }
}

exports.getAllAccount = async(req, res, next) => {
    try {
        const [accounts, _] = await Account.getAllAccount();
        console.log("get all account.")
        res.status(200).json({length: accounts.length, accounts});
        
    } catch (error) {
        next(error);
    }
}

exports.getAccountByPhoneNumber = async (req, res, next) => {
    try {
        let phone_number = req.params.phone_number;
        console.log("phone_number: "+phone_number);
        let [account,_] = await Account.getAccountByPhoneNumber(phone_number);
        res.status(200).json({account: account});

    } catch (error) {
        next(error);
    }
}

exports.getAccountByUserName = async(req, res, next) => {
    try {
        let user_name = req.params.user_name;
        console.log("user_name: "+user_name)
        let [account, _] = await Account.getAccountByUserName(user_name);
        res.status(200).json({account: account});

    } catch (error) {
        next(error);
    }
}

exports.getAccount = async(req, res, next) => {
    try {
        let user_name = req.query.user_name;
        let phone_number = req.query.phone_number;
        if(phone_number){
            console.log("phone_number: " + phone_number);
            const [account, _] = await Account.getAccountByPhoneNumber(phone_number);
            res.status(200).json({account}); 
        } 
        else if(user_name){
            console.log("user_name: " + user_name);
            const [account,_] = await Account.getAccountByUserName(user_name);
            res.status(200).json({account}); 
        }else{
            console.log("get all account")
            const [accounts, _] = await Account.getAllAccount();
            res.status(200).json({length: accounts.length, accounts});
        }
        
    } catch (error) {
        next(error);
    }
}

exports.getAccountById = async(req, res, next) => {
    try {
        
        const id = req.params.id;
        const [account, _] = await Account.getAccountById(id);
        res.status(200).json({account});

    } catch (error) {
        next(error);
    }
}

exports.putAccount = async(req, res, next) => {
    try {
        let {
            phone_number, 
            user_id,
            user_name,
            password
        } = req.body;
        
        if(phone_number){
            let account = new Account(phone_number, user_id, user_name, password);
            const [result,_] = await account.update();
            if(result.affectedRows == 0){
                res.status(204).json({message:`Not found account with phone_number = ${phone_number}`})
            }
            else res.status(200).json({message:"Update success!"})
        }else{
            res.status(400).json({message:"phone_number require"});
        }
        
    } catch (error) {
        next(error)
    }
}