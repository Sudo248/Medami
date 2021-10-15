const express = require('express');

const activityController = require('../controllers/activityController');
const Activity = require('../database/models/Activity');

const router = express.Router();

router
    .route('/')
    .get(activityController.getActivity)
    .post(activityController.postActivity)
    .put(activityController.putActivity);

router
    .route('/:id')
    .get(activityController.getActivityById)
    .delete(activityController.deleteActivity);

module.exports = router;