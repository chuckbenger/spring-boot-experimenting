import {useState} from "react";
import {Button, Form, FormGroup, Input, Label} from "reactstrap";


const CreateTweet = () => {
    const [message, setMessage] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();

        const response = await fetch("/api/tweets", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token"),
            },
            body: JSON.stringify({
                message,
            }),
        });

        const tweet = await response.json();

        console.log("finished creating tweet" + tweet);
    }

    return (
        <Form onSubmit={handleSubmit}>
            <FormGroup>
                <Label for="message">Message</Label>
                <Input
                    type="text"
                    name="message"
                    id="message"
                    placeholder="Message"
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                />
            </FormGroup>
            <Button type="submit">Create Tweet</Button>
        </Form>
    )
}
export default CreateTweet;