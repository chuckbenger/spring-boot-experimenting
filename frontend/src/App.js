import logo from './logo.svg';
import './App.css';
import {Component} from "react";
import TweetList from "./components/TweetList";
import {Route, BrowserRouter as Router, Switch, withRouter} from "react-router-dom";
import Home from "./components/Home";
import AppNavbar from "./components/AppNavBar";
import Register from "./components/Register";
import Login from "./components/Login";
import CreateTweet from "./components/CreateTweet";

class App extends Component {
    render() {
        return <Router>
            <AppNavbar/>
            <Switch>
                <Route path="/" exact={true} component={Home}/>
                <Route path="/tweets" exact={true} component={TweetList}/>
                <Route path="/register" exact={true} component={Register}/>
                <Route path="/login" exact={true} component={Login}/>
                <Route path="/tweets/new" exact={true} component={CreateTweet}/>
            </Switch>
        </Router>
    }
}

export default App;
