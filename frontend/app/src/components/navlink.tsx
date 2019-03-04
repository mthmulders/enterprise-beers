import NavLink_ from 'reactstrap/lib/NavLink';

import { LinkProps } from './navigation';

/**
 * Extensions of Reactstraps NavLink element that enables using React Routers Link element as Tag.
 */
class NavLink extends NavLink_<LinkProps> {
}

export default NavLink;
