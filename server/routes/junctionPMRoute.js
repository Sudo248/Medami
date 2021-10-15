const express = require('express');

const junctionController = require('../controllers/junctionPMController');

const router = express.Router();

router
    .route('/')
    .get(junctionController.getJunctionPM)
    .post(junctionController.postJunctionPM)
    .put(junctionController.putJunctionPM)
    .delete(junctionController.deleteJunctionPM);


module.exports = router;