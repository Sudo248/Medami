const JunctionPM = require('../database/models/JunctionPrescriptionMedicine');

module.exports.getJunctionPM = async(req, res, next) => {
    try {
        
        const query = req.query;
        let junctionPMs;

        if(query.prescription_id){
            [junctionPMs,_] = await JunctionPM.getJunctionByPrescriptionId(query.prescription_id);
        }
        else if(query.medicine_id){
            [junctionPMs,_] = await JunctionPM.getJunctionByMedicineId(query.medicine_id);
        }
        else{
            [junctionPMs,_] = await JunctionPM.getAllJunctionPM();
        }

        // console.log('notifications: ', prescriptions)
        res.status(200).json({length: junctionPMs.length, junctionPMs});

    } catch (error) {
        next(error)
    }
}

module.exports.postJunctionPM = async(req, res, next) => {
    try {
        
        const {
            prescription_id,
            medicine_id
        } = req.body;

        const junctionPM = new JunctionPM(
            prescription_id,
            medicine_id
        )

        await junctionPM.insert();

        res.status(201).json({message:"Insert junction_prescription_medicine success!"});

    } catch (error) {
        next(error);
    }
}

module.exports.putJunctionPM = async(req, res, next) => {
    try {
        
        const {
            prescription_id,
            medicine_id
        } = req.body;

        const junctionPM = new JunctionPM(
            prescription_id,
            medicine_id
        )

        if(prescription_id || medicine_id){
            if(req.query.prescription_id){
                const [result, _] = await junctionPM.updateByPrescriptionId();
                if(result.affectedRows == 0){
                    res.status(204).json({message:`Not found junction_prescription_medicine with prescription_id = ${prescription_id}`});
                }
                else res.status(200).json({message:"Update junction_prescription_medicine Success!"})
            }
            else{
                const [result, _] = await junctionPM.updateByMedicineId();
                if(result.affectedRows == 0){
                    res.status(204).json({message:`Not found junction_prescription_medicine with medicine_id = ${medicine_id}`});
                }
                else res.status(200).json({message:"Update junction_prescription_medicine Success!"})
            }
                
        }else{
            res.status(400).json({message:"prescription_id and medicine_id require"});
        }

    } catch (error) {
        next(error);
    }
}

module.exports.deleteJunctionPM = async(req, res, next) => {
    try {

        if(req.query.prescription_id){
            await JunctionPM.deleteByPrescriptionId(req.query.prescription_id);
        }else{
            await JunctionPM.deleteByMedicineId(req.query.medicine_id);
        }
        res.status(200).json({message:"Delete junction_prescription_medicine success!"})

    } catch (error) {
        next(error);
    }
}