const express = require('express');
const prescriptionController = require('../controllers/prescriptionController');
const router = express.Router();

router
    .route('/')
    .get(prescriptionController.getPrescription)
    .post(prescriptionController.postPrescription)
    .put(prescriptionController.putPrescription);

router
    .route('/:id')
    .get(prescriptionController.getPrescriptionById)
    .delete(prescriptionController.deletePrescriotionById);


module.exports = router