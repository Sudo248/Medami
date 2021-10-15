const Activity = require('../database/models/Activity');

module.exports.getActivity = async(req, res, next) => {
    try {
        
        const query = req.query;
        let activities;
        if(query.user_id){
            [activities,_] = await Activity.getActivitiesByUserId(query.user_id);
        }else{
            [activities,_] = await Activity.getAllActivities();
        }
        res.status(200).json({length:activities.length, activities});

    } catch (error) {
        next(error)
    }
}

module.exports.getActivityById = async(req, res, next) => {
    try {
        
        const id = req.params.id;
        const [activity,_] = await Activity.getAllAcivities;
        res.status(200).json({activity});

    } catch (error) {
        next(error)
    }
}

module.exports.postActivity = async(req, res, next) => {
    try {
        
        const {
            user_id ,
            name ,
            note 
        } = req.body;

        const activity = new Activity(null, user_id, name, note);

        await activity.insert();

        res.status(201).json({message:"Insert activity success!"});

    } catch (error) {
        next(error)
    }
}

module.exports.putActivity = async(req, res, next) => {
    try {
        
        const {
            activity_id,
            user_id ,
            name ,
            note 
        } = req.body;
        if(activity_id){
            const activity = new Activity(activity_id, user_id, name, note);

            const [result,_] = await activity.update();

            if(result.affectedRows == 0 ){
                res.status(204).json({message:`Not found activity with activity_id = ${activity_id}`});
            }else{
                res.status(200).json({message:"Update activity success!"});
            }

        }else{
            res.status(400).json({message:"activity_id require"})
        }

    } catch (error) {
        next(error)
    }
}

module.exports.deleteActivity = async(req, res, next) => {
    try {
        
        const id = req.params.id;
        await Activity.deleteActivityById(id);  
        res.status(200).json({message:"Delete Activity success!"})

    } catch (error) {
        next(error);
    }
}

