const express = require('express');
const accountController = require('../controllers/accountController');
const router = express.Router();

router
    .route('/')
    .get(accountController.getAllAccount)
    .post(accountController.insertAccount);

router.route(':/phoneOrEmail').get(accountController.getAccountByEmailOrPhone)

module.exports = router;