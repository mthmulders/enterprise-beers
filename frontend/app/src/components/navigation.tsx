import * as React from 'react';

import { Link } from 'react-router-dom';
import {
    Button,
    Collapse,
    Container,
    Form,
    Input,
    Nav,
    Navbar,
    NavbarBrand,
    NavbarToggler,
    NavItem,
} from 'reactstrap';

import DropdownItem from './dropdownitem';
import NavLink from './navlink';

//
// Extensions of Reactstraps DropdownItem and NavLink elements that
// enable using React Routers Link element as Tag.
//
export interface LinkProps {
    to: string;
}

interface NavigationState {
    isOpen: boolean;
}

class Navigation extends React.Component<{}, NavigationState> {
    constructor(props: {}) {
        super(props);
        this.state = { isOpen: false };
    }

    render() {
        return (
            <Navbar color='primary' dark={ true } expand='md'>
                <Container>
                    <NavbarBrand href="/">Enterprise Beers</NavbarBrand>
                    <NavbarToggler onClick={ this.toggle } />
                    <Collapse isOpen={ this.state.isOpen } navbar={ true }>
                        <Nav className='md-auto' navbar={ true }>
                            { this.menuItem('Home', '/', 'home') }
                            { this.menuItem('Beers', '/beers', 'beer') }
                            { this.menuItem('Breweries', '/breweries', 'industry') }
                        </Nav>
                    </Collapse>
                    <Form className='form-inline my-2 my-lg-0'>
                        <Input type='search' className='mr-sm-2' placeholder='Search' aria-label='Search' />
                        <Button className='btn-outline my-2 my-sm-0' type='submit'>
                            <i className='fa fa-search'></i> &nbsp; Search
                        </Button>
                    </Form>
                </Container>
            </Navbar>
        );
    }

    private menuItem = (title: string, target: string, iconId?: string) => {
        const icon = iconId ? <i className={ `fa fa-${iconId}` }></i> : null;

        return (
            <NavItem key={ target }>
                <NavLink tag={ Link } to={ target }>
                    { icon } &nbsp; { title }
                </NavLink>
            </NavItem>
        );
}

    private subMenuItem = (title: string, target: string) => (
        <DropdownItem tag={ Link } to={ target }>{ title }</DropdownItem>
    )

    private toggle = () => {
        this.setState((prev, props) => ({
            isOpen: !prev.isOpen,
        }));
    }
}

export default Navigation;
