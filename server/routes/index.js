const accountRoute = require('./accountRoute');
const userRoute = require('./userRoute');
const prescriptionRoute = require('./prescriptionRoute');
const medicineRoute = require('./medicineRoute');
const activityRoute = require('./activityRoute');
const notificationRoute = require('./notificationRoute');
const junctionPMRoute = require('./junctionPMRoute');

function routes(app){
    app.use(express.json());
    app.use('/accounts', accountRoute);
    app.use('/users', userRoute);
    app.use('/prescriptions', prescriptionRoute);
    app.use('/medicines', medicineRoute);
    app.use('/activities', activityRoute);
    app.use('/notifications', notificationRoute);
    app.use('/junctionpms',junctionPMRoute);
    
    app.use((err, req, res, next) => {
        console.log(err.stack);
        console.log(err.name);
        console.log(err.code);
      
        res.status(500).json({
          message: "Something went rely wrong",
        });
    });
}

module.exports = routes;
