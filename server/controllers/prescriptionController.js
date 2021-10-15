const Prescription = require('../database/models/Prescription');

exports.getPrescription = async(req, res, next) => {
    try {
        
        const query = req.query;
        let prescriptions;
        if(query.user_id){
            if(query.is_using){
                console.log(`get Prescription by user_id: ${query.user_id} and is_using: ${query.is_using}`);
                [prescriptions,_] = await Prescription.getPrescriptionsByUserIdAndIsUsing(query.user_id, query.is_using);
            }else{
                console.log(`get prescription by user_id: ${query.user_id}`);
                [prescriptions,_] = await Prescription.getPrescriptionsByUserId(query.user_id);
            }
        }else{
            [prescriptions,_] = await Prescription.getAllPrescriptions();
        }
        console.log('prescription: ', prescriptions)
        res.status(200).json({length: prescriptions.length, prescriptions});


    } catch (error) {
        next(error)
    }
}

exports.getPrescriptionById = async(req, res, next) => {
    try {
        
        const id = req.params.id;
        console.log('get prescription by id ', id);
        const [prescription,_] = await Prescription.getPrescriptionById(id);
        console.log("prescription",prescription);
        res.status(200).json({prescription});

    } catch (error) {
        next(error)
    }
}

exports.postPrescription = async(req, res, next) => {
    try {
        const {
            user_id,
            diagnose,
            date ,
            is_using,
            type,
            name_of_doctor ,
            advice_of_doctor
        } = req.body; 

        const prescription = new Prescription(
            null,
            user_id,
            diagnose,
            date ,
            is_using,
            type,
            name_of_doctor ,
            advice_of_doctor
        );

        await prescription.insert();

        res.status(201).json({message:"Insert prescription success!"});

    } catch (error) {
        next(error);
    }
}

exports.putPrescription = async(req, res, next) => {
    try {
        
        const {
            prescription_id,
            user_id,
            diagnose,
            date ,
            is_using,
            type,
            name_of_doctor ,
            advice_of_doctor
        } = req.body;

        const prescription = new Prescription(
            prescription_id,
            user_id,
            diagnose,
            date ,
            is_using,
            type,
            name_of_doctor ,
            advice_of_doctor
        );

        if(prescription_id){
            const [result, _] = await prescription.update();
            if(result.affectedRows == 0){
                res.status(204).json({message:`Not found prescription with id = ${prescription_id}`});
            }
            else res.status(200).json({message:"Update prescription Success!"})    
        }else{
            res.status(400).json({message:"prescription_id require"});
        }
        
    } catch (error) {
        next(error)
    }
}

exports.deletePrescriotionById = async(req, res, next) => {
    try {
        
        const id = req.params.id;

        await Prescription.deletePrescriptionById(id);

        res.status(200).json({message:"Delete prescription success!"})

    } catch (error) {
        next(error);
    }
}