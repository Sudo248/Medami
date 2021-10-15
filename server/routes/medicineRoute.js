const express = require('express');
const medicineController = require('../controllers/medicineController');

const router = express.Router();

router
    .route('/')
    .get(medicineController.getMedicine)
    .post(medicineController.postMedicine)
    .put(medicineController.putMedicine);

router
    .route('/:id')
    .get(medicineController.getMedicineById)
    .delete(medicineController.deleteMedicine);

module.exports = router;
