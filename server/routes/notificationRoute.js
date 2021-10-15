const express = require('express');

const notificationController = require('../controllers/notificationController');

const router = express.Router();

router
    .route('/')
    .get(notificationController.getNotification)
    .post(notificationController.postNotification)
    .put(notificationController.putNotification);

router
    .route('/:id')
    .get(notificationController.getNotificationById)
    .delete(notificationController.deleteNotification);

module.exports = router;