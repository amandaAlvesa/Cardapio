import { useEffect, useState } from "react"
import { useFoodDataMutate } from "../../hooks/useFoodDataMutate";
import { FoodData } from "../../interface/FoodData";
import  "./modal.css";

interface InputProps {
    label: string,
    value: string | number,
    updateValue(value: any): void
}   

interface ModalProps{
    closeModal():void
}

const Input = ({label, value, updateValue}: InputProps) =>{
    return(
        <>
        <label>{label}</label>
        <input value={value} onChange={event => updateValue(event.target.value)} />
        </>
    )
}

export function CreateModal({closeModal}: ModalProps){
    const [title, setTitle] = useState("");
    const [price, setPrice] = useState(0);
    const [image, setImage] = useState("");
    const { mutate, isSuccess, isLoading } = useFoodDataMutate();

    const subumit = () => {
        const foodData: FoodData = {
            title,
            price,
            image,
        }
        mutate(foodData)
    } 

    useEffect(() =>{
        if(!isSuccess) return
        closeModal();
    }, [isSuccess])

    return (
        <div className="modal-overlay">
            <div className="modal-body">
                <h2>Cadastre um novo item no cardapio</h2>
                <form action="input-cantainer">
                <Input label="title" value={title} updateValue={setTitle}></Input>
                <Input label="price" value={price} updateValue={setPrice}></Input>
                <Input label="image" value={image} updateValue={setImage}></Input>
                </form>
                <button onClick={subumit} className="btn-secondary">
                    {isLoading ? 'postando...' : 'postar'}
                </button>
            </div>
        </div>
    )
}