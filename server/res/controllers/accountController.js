const Account = require('../models/Account');

exports.insertAccount = async (req, res, next) => {
    try{
        let {
            emailOrPhone, 
            userId,
            password
        } = req.body;

        let account = new Account(emailOrPhone, userId, password);
        account = await account.insert();
        res.status(201).json({message:"Insert Account Success"});
        
    }catch(error){
        next(error);
    }
}

exports.getAllAccount = async(req, res, next) => {
    try {
        const [accounts, _] = await Account.getAllAccount();
        res.status(200).json({count: accounts.length, accounts});
        
    } catch (error) {
        next(error);
    }
}

exports.getAccountByEmailOrPhone = async (req, res, next) => {
    try {
        let emailOrPhone = req.params.emailOrPhone;
        let [account,_] = await Account.getAccountByEmailOrPhone(emailOrPhone);
        res.status(200).json({account: account[0]});

    } catch (error) {
        next(error);
    }
}