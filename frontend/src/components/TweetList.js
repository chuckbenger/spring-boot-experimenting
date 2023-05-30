import {Component} from "react";
import {Button, Container, Table} from "reactstrap";
import {Link, withRouter} from "react-router-dom";
import AppNavbar from "./AppNavBar";

class TweetList extends Component {
    constructor(props) {
        super(props);
        this.state = {tweets: []};
    }

    componentDidMount() {
        fetch('/api/tweets')
            .then(response => response.json())
            .then(tweets => this.setState({tweets}));
    }

    render() {
        const {tweets, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>
        }

        const tweetList = tweets.map(tweet => {
            return <tr key={tweet.id}>
                <td style={{whiteSpace: 'nowrap'}}>{tweet.message}</td>
            </tr>
        })

        return <div>
            <Container fluid>
                <div className="float-right">
                    <Button color="success" tag={Link} to="/clients/new">Add Tweet</Button>
                </div>
                <h3>Tweets</h3>
                <h3>Clients</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="30%">Tweet</th>
                    </tr>
                    </thead>
                    <tbody>
                    {tweetList}
                    </tbody>
                </Table>
            </Container>
        </div>
    }
}
export default TweetList;