const Notification = require('../database/models/Notification');

module.exports.getNotification = async(req, res, next) => {
    try {
        
        const query = req.query;
        let notifications;

        if(query.medicine_id){
            [notifications,_] = await Notification.getNotificationByMedicineId(query.medicine_id);
        }
        else if(query.activity_id){
            [notifications,_] = await Notification.getNotificationByActivityId(query.activity_id);
        }
        else{
            [notifications,_] = await Notification.getAllNotifications();
        }

        // console.log('notifications: ', prescriptions)
        res.status(200).json({length: notifications.length, notifications});

    } catch (error) {
        next(error)
    }
}

module.exports.getNotificationById = async(req, res, next) => {
    try {
        
        const id = req.params.id;
        const [notification,_] = await Notification.getNotificationById(id);
        console.log("notification", notification);
        res.status(200).json({notification});

    } catch (error) {
        next(error)
    }
}

module.exports.postNotification = async(req, res, next) => {
    try {
        
        const {
            medicine_id,
            activity_id,
            date_time,
            note
        } = req.body;

        const notification = new Notification(
            null,
            medicine_id,
            activity_id,
            date_time,
            note
        )

        await notification.insert();

        res.status(201).json({message:"Insert notification success!"});

    } catch (error) {
        next(error);
    }
}

module.exports.putNotification = async(req, res, next) => {
    try {
        
        const {
            notification_id,
            medicine_id,
            activity_id,
            date_time,
            note
        } = req.body;

        const notification = new Notification(
            notification_id,
            medicine_id,
            activity_id,
            date_time,
            note
        )

        if(notification_id){
            const [result, _] = await notification.update();
            if(result.affectedRows == 0){
                res.status(204).json({message:`Not found notification with id = ${notification_id}`});
            }
            else res.status(200).json({message:"Update notification Success!"})    
        }else{
            res.status(400).json({message:"notification_id require"});
        }

    } catch (error) {
        next(error);
    }
}

module.exports.deleteNotification = async(req, res, next) => {
    try {
        
        const id = req.params.id;

        await Notification.deleteNotificationById(id)

        res.status(200).json({message:"Delete notification success!"})

    } catch (error) {
        next(error);
    }
}