import DropdownItem_ from 'reactstrap/lib/DropdownItem';

import { LinkProps } from './navigation';

/**
 * Extensions of Reactstraps DropdownItem element that enables using React Routers Link element as Tag.
 */
class DropdownItem extends DropdownItem_<LinkProps> {
}

export default DropdownItem;
