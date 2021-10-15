const Medicine = require('../database/models/Medicine');

module.exports.getMedicine = async(req, res, next) => {
    try {

        const [medicines,_] = await Medicine.getAllMedicine();
        res.status(200).json({length:medicines.length, medicines});
        
    } catch (error) {
        next(error);
    }
}

module.exports.getMedicineById = async(req, res, next) => {
    try {
        
        const id = req.params.id;
        const [medicine,_] = await Medicine.getMedicineById(id);
        res.status(200).json({medicine});

    } catch (error) {
        next(error);
    }
}

module.exports.postMedicine = async(req, res, next) => {
    try {
        
        const{
            name,
            weekly_frequency,
            time_take_pill,
            times_a_day,
            amount_one_time,
            remaining,
            unit,
            from_date,
            to_date
        } = req.body;

        const medicine = new Medicine(
            null,
            name,
            weekly_frequency,
            time_take_pill,
            times_a_day,
            amount_one_time,
            remaining,
            unit,
            from_date,
            to_date
        );

        await medicine.insert();

        res.status(201).json({message:"Insert Medicine success!"});

    } catch (error) {
        next(error);
    }
}

module.exports.putMedicine = async(req, res, next) => {
    try {
        
        const{
            medicine_id,
            name,
            weekly_frequency,
            time_take_pill,
            times_a_day,
            amount_one_time,
            remaining,
            unit,
            from_date,
            to_date
        } = req.body;

        const medicine = new Medicine(
            medicine_id,
            name,
            weekly_frequency,
            time_take_pill,
            times_a_day,
            amount_one_time,
            remaining,
            unit,
            from_date,
            to_date
        );

        const [result,_] = await medicine.update();
        if(medicine_id){
            if(result.affectedRows == 0){
                res.status(204).json({message:`Not Found medicine with id = ${medicine_id}`})
            }
            else res.status(200).json({message:"Update Medicine success!"});
    
        }else{
            res.status(400).json({message:"medicine_id require"});
        }
        
    } catch (error) {
        next(error);
    }
}

module.exports.deleteMedicine = async(req, res, next) => {
    try {
        
        const id = req.params.id;
        await Medicine.deleteMedicineById(id);  
        res.status(200).json({message:"Delete Medicine success!"})

    } catch (error) {
        next(error);
    }
}