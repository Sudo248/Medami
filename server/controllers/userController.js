const User = require('../database/models/User');

exports.getUserById = async(req, res, next) => {
    try {
        let id = req.params.id;
        console.log("Get user by id = ",id);
        let [user,_] = await User.getUserById(id);
        res.status(200).json({user});
    } catch (error) {
        next(error);
    }
}

exports.getUser = async(req, res, next) => {
    try {
        console.log("Get all user");
        const [users,_] = await User.getAllUsers();
        res.status(200).json({length:users.length, users}) 
        
    } catch (error) {
        next(error)
    }
}

exports.postUser = async(req, res, next) => {
    try {
        let {
            first_name,
            last_name,
            phone_number,
            date_of_birth,
            weight,
            height,
        } = req.body;

        let user = new User(null, first_name, last_name, phone_number, date_of_birth, weight, height);
        await user.insert();
        res.status(201).json({message:"Insert User Success!"})
    } catch (error) {
        next(error);
    }
}

exports.putUser = async(req, res, next) => {
    try {
        let {
            user_id,
            first_name,
            last_name,
            phone_number,
            date_of_birth,
            weight,
            height,
        } = req.body;
        if(user_id){
            let user = new User(user_id, first_name, last_name, phone_number, date_of_birth, weight, height);
            const [result,_] = await user.update();
            if(result.affectedRows == 0){
                res.status(204).json({message:`Not found user with id = ${user_id}`});
            }
            else res.status(200).json({message:"Update User Success!"})
        }else{
            res.status(400).json({message:"user_id require"});
        }
        
    } catch (error) {
        next(error)
    }
}
