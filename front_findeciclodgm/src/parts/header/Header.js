import { Fragment, useState } from 'react'
import { Dialog, Popover, Tab, Transition } from '@headlessui/react'
import { MenuIcon, ShoppingBagIcon, XIcon, UserIcon } from '@heroicons/react/outline'
import { Link } from 'react-router-dom';

import logo from '../../img/logo.png';
import bandera from '../../img/bandera_espana.png';
import somos from '../../img/quienes-somos.jpg';
import contacto from '../../img/contacto.jpg';

const navigation = {
  categories: [
    {
      id: 'DCine',
      name: 'DCine',
      featured: [
        {
          name: 'Quiénes somos',
          description: 'Conócenos ya',
          href: '/quienes-somos',
          imageSrc: somos,
          imageAlt: 'Quiénes somos',
        },
        {
          name: 'Contacta con nosotros',
          description: 'Queremos saber tu opinión',
          href: 'contacto',
          imageSrc: contacto,
          imageAlt: 'Contacta con nosotros',
        },
      ],
      sections: [
        {
          id: 'generos',
          name: 'Géneros',
          items: [
            { name: 'Acción', href: '#' },
            { name: 'Animación', href: '#' },
            { name: 'Anime', href: '#' },
            { name: 'Aventuras', href: '#' },
            { name: 'Bélico', href: '#' },
            { name: 'Ciencia ficción', href: '#' },
            { name: 'Comedia', href: '#' },
            { name: 'Drama', href: '#' },
            { name: 'Fantástico', href: '#' },
            { name: 'Histórico', href: '#' },
            { name: 'Policíaco', href: '#' },
            { name: 'Suspense', href: '#' },
            { name: 'Terror', href: '#' },
            { name: 'Thiller', href: '#' },
            { name: 'Western', href: '#' },
          ],
        }
      ],
    }
  ],
  pages: [
    { name: 'Quiénes somos', href: '/quienes-somos' },
    { name: 'Contacta con nosotros', href: '/contacto' },
  ],
}

function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}

export default function Header(props) {

  const [open, setOpen] = useState(false)
  const { setOpenCart } = props;

  return (
      <div className="bg-white">
        {/* Menu de móvil */}
        <Transition.Root show={open} as={Fragment}>
          <Dialog as="div" className="relative z-40 lg:hidden" onClose={setOpen}>
            <Transition.Child
              as={Fragment}
              enter="transition-opacity ease-linear duration-300"
              enterFrom="opacity-0"
              enterTo="opacity-100"
              leave="transition-opacity ease-linear duration-300"
              leaveFrom="opacity-100"
              leaveTo="opacity-0"
            >
              <div className="fixed inset-0 bg-black bg-opacity-25" />
            </Transition.Child>

            <div className="fixed inset-0 flex z-40">
              <Transition.Child
                as={Fragment}
                enter="transition ease-in-out duration-300 transform"
                enterFrom="-translate-x-full"
                enterTo="translate-x-0"
                leave="transition ease-in-out duration-300 transform"
                leaveFrom="translate-x-0"
                leaveTo="-translate-x-full"
              >
                <Dialog.Panel className="relative max-w-xs w-full bg-white shadow-xl pb-12 flex flex-col overflow-y-auto">
                  <div className="px-4 pt-5 pb-2 flex">
                    <button
                      type="button"
                      className="-m-2 p-2 rounded-md inline-flex items-center justify-center text-gray-400"
                      onClick={() => setOpen(false)}
                    >
                      <span className="sr-only">Close menu</span>
                      <XIcon className="h-6 w-6" aria-hidden="true" />
                    </button>
                  </div>

                  {/* Links */}
                  <Tab.Group as="div" className="mt-2">
                    <div className="border-b border-gray-200">
                      <Tab.List className="-mb-px flex px-4 space-x-8">
                          <Tab
                            key='DCine'
                            className={({ selected }) =>
                              classNames(
                                selected ? 'border-principal' : 'text-gray-900 border-transparent',
                                'flex-1 whitespace-nowrap py-4 px-1 border-b-2 text-base font-medium'
                              )
                            }
                          >
                            DCine
                          </Tab>
                      </Tab.List>
                    </div>
                    <Tab.Panels as={Fragment}>
                      {navigation.categories.map((category) => (
                        <Tab.Panel key={category.name} className="pt-10 pb-8 px-4 space-y-10">
                          <div className="grid grid-cols-2 gap-x-4">
                            {category.featured.map((item) => (
                              <div key={item.name} className="group relative text-sm">
                                <div className="aspect-w-1 aspect-h-1 rounded-lg bg-gray-100 overflow-hidden group-hover:opacity-75">
                                  <img src={item.imageSrc} alt={item.imageAlt} className="object-center object-cover" />
                                </div>
                                <Link to={item.href} className="mt-6 block font-medium text-gray-900">
                                  <span className="absolute z-10 inset-0" aria-hidden="true" />
                                  {item.name}
                                </Link>
                                <p aria-hidden="true" className="mt-1">
                                  {item.description}
                                </p>
                              </div>
                            ))}
                          </div>
                          {category.sections.map((section) => (
                            <div key={section.name}>
                              <p id={`${category.id}-${section.id}-heading-mobile`} className="font-medium text-gray-900">
                                {section.name}
                              </p>
                              <ul
                                role="list"
                                aria-labelledby={`${category.id}-${section.id}-heading-mobile`}
                                className="mt-6 flex flex-col space-y-6"
                              >
                                {section.items.map((item) => (
                                  <li key={item.name} className="flow-root">
                                    <Link to={item.href} className="-m-2 p-2 block text-gray-500">
                                      {item.name}
                                    </Link>
                                  </li>
                                ))}
                              </ul>
                            </div>
                          ))}
                        </Tab.Panel>
                      ))}
                    </Tab.Panels>
                  </Tab.Group>

                  <div className="border-t border-gray-200 py-6 px-4 space-y-6">
                    {navigation.pages.map((page) => (
                      <div key={page.name} className="flow-root">
                        <Link to={page.href} className="-m-2 p-2 block font-medium text-gray-900">
                          {page.name}
                        </Link>
                      </div>
                    ))}
                  </div>

                  <div className="border-t border-gray-200 py-6 px-4 space-y-6">
                    <div className="flow-root">
                      <Link to="/login" className="-m-2 p-2 block font-medium text-gray-900">
                        Inicia sesión
                      </Link>
                    </div>
                    <div className="flow-root">
                      <Link to="/signup" className="-m-2 p-2 block font-medium text-gray-900">
                        Crea una cuenta con nosotros
                      </Link>
                    </div>
                  </div>

                  <div className="border-t border-gray-200 py-6 px-4">
                    <a href="#" className="-m-2 p-2 flex items-center">
                      <img
                        src={bandera}
                        alt="Bandera española"
                        className="w-5 h-auto block flex-shrink-0"
                      />
                      <span className="ml-3 block text-base font-medium text-gray-900">ESP</span>
                      <span className="sr-only">, change currency</span>
                    </a>
                  </div>
                </Dialog.Panel>
              </Transition.Child>
            </div>
          </Dialog>
        </Transition.Root>
        {/* Menu de escritorio */}
        <header className="relative bg-white">
          <p className="bg-principal h-10 flex items-center justify-center text-sm font-medium text-white px-4 sm:px-6 lg:px-8">
            Gastos de envío gratuitos a partir de 40€
          </p>

          <nav aria-label="Top" className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div className="border-b border-gray-200">
              <div className="h-16 flex items-center">
                <button
                  type="button"
                  className="bg-white p-2 rounded-md text-gray-400 lg:hidden"
                  onClick={() => setOpen(true)}
                >
                  <span className="sr-only">Abrir menú</span>
                  <MenuIcon className="h-6 w-6" aria-hidden="true" />
                </button>

                {/* Logo */}
                <div className="ml-4 flex lg:ml-0 rounded">
                  <Link to="/index">
                    <img
                      className="h-12"
                      src={logo}
                      alt="Logo de la tienda"
                    />
                  </Link>
                </div>

                {/* Flyout menus */}
                <Popover.Group className="hidden lg:ml-8 lg:block lg:self-stretch">
                  <div className="h-full flex space-x-8">
                    <Link
                        key='peliculas'
                        to='/peliculas'
                        className="flex items-center text-sm font-medium text-gray-700 hover:text-gray-800"
                    >
                      Películas
                    </Link>
                    <Link
                        key='quines-somos'
                        to='/quienes-somos'
                        className="flex items-center text-sm font-medium text-gray-700 hover:text-gray-800"
                    >
                      Quiénes somos
                    </Link>
                    <Link
                        key='contacto'
                        to='/contacto'
                        className="flex items-center text-sm font-medium text-gray-700 hover:text-gray-800"
                    >
                      Contacta con nosotros
                    </Link>
                  </div>
                </Popover.Group>

                <div className="ml-auto flex items-center">
                  <div className="hidden lg:flex lg:flex-1 lg:items-center lg:justify-end lg:space-x-6">
                    <Link to="/login" className="text-sm font-medium text-gray-700 hover:text-gray-800">
                      Inicia sesión
                    </Link>
                    <span className="h-6 w-px bg-gray-200" aria-hidden="true" />
                    <Link to="/signup" className="text-sm font-medium text-gray-700 hover:text-gray-800">
                      Crea una cuenta con nosotros
                    </Link>
                  </div>

                  <div className="hidden lg:ml-8 lg:flex">
                    <a href="#" className="text-gray-700 hover:text-gray-800 flex items-center">
                      <img
                        src={bandera}
                        alt="Bandera española"
                        className="w-5 h-auto block flex-shrink-0"
                      />
                      <span className="ml-3 block text-sm font-medium">ESP</span>
                      <span className="sr-only">, change currency</span>
                    </a>
                  </div>

                  {/* Account button */}
                  <div className="flex lg:ml-6">
                    <Link to="/cuenta" className="p-2 text-gray-400 hover:text-gray-500">
                      <UserIcon className="w-6 h-6" aria-hidden="true" />
                    </Link>
                  </div>

                  {/* Cart */}
                  <div className="ml-4 flow-root lg:ml-6">
                    <button className="group -m-2 p-2 flex items-center" onClick={() => setOpenCart(true)}>
                      <ShoppingBagIcon
                        className="flex-shrink-0 h-6 w-6 text-gray-400 group-hover:text-gray-500"
                        aria-hidden="true"
                      />
                      <span className="ml-2 text-sm font-medium text-gray-700 group-hover:text-gray-800">0</span>
                      <span className="sr-only">items in cart, view bag</span>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </nav>
        </header>
      </div>
  )
}
