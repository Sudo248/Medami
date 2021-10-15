const express = require('express');
const accountController = require('../controllers/accountController');
const router = express.Router();

router
    .route('/')
    .get(accountController.getAccount)
    .post(accountController.postAccount)
    .put(accountController.putAccount)

router.route('/:id').get(accountController.getAccountById);
// router.route('/:phone_number').get(accountController.getAccountByPhoneNumber);
// router.route('/:user_name').get(accountController.getAccountByUserName);


module.exports = router;