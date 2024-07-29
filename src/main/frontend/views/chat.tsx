import {Button, TextField} from "@vaadin/react-components";
import {useState} from "react";
import {ChatService} from "Frontend/generated/endpoints";
import Markdown from "react-markdown";

export default function chat(){
    const[questions,setQuestions]=useState<string>("");
    const [response,setResponse]=useState<string>("");
    async function send(){
        ChatService.regChat(questions).then(res=>{
            setResponse(res);
        })
    }

    return(
    <div className="p-m">
        <h3>Chat Bot</h3>
        <div>
            <TextField  style={{width:'80%'}}
                        onChange={(e)=>setQuestions(e.target.value)}/>
            <Button theme="primary" className="mx-1" onClick={send}> Send </Button>
            <div>
                <Markdown>
                    {response}
                </Markdown>
            </div>
        </div>
    </div>
)
}
