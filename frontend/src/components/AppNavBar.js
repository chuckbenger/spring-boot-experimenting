import {Navbar, NavbarBrand} from "reactstrap";
import {Link} from "react-router-dom";
import {Component} from "react";

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
            <NavbarBrand tag={Link} to="/tweets">Tweets</NavbarBrand>
            <NavbarBrand tag={Link} to="/register">Register</NavbarBrand>
            <NavbarBrand tag={Link} to="/login">Login</NavbarBrand>
            <NavbarBrand tag={Link} to="/tweets/new">CreateTweet</NavbarBrand>
        </Navbar>;
    }
}