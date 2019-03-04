import * as React from 'react';

// As suggested at https://github.com/reactstrap/reactstrap/issues/336#issuecomment-281396777
import { NavLink as RRNavLink } from 'react-router-dom';
import {
    Button,
    Collapse,
    Container,
    Form,
    Input,
    Nav,
    Navbar,
    NavLink,
    NavbarBrand,
    NavbarToggler,
    NavItem,
} from 'reactstrap';

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
                <NavLink tag={ RRNavLink } to={ target } exact={ target === '/' }>
                    { icon } &nbsp; { title }
                </NavLink>
            </NavItem>
        );
    }

    private toggle = () => {
        this.setState((prev, props) => ({
            isOpen: !prev.isOpen,
        }));
    }
}

export default Navigation;
