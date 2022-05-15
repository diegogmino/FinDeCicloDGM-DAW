import { Link } from 'react-router-dom';
import Pagination from "../pagination/Pagination"

import cuervo from '../../img/portadas/el-cuervo.jpg';
import padrino from '../../img/portadas/el-padrino.jpg';

const products = [
    {
      id: 1,
      name: 'El cuervo',
      href: '/detalle/1',
      imageSrc: cuervo,
      imageAlt: "El cuervo",
      price: '9.99€',
    },
    {
      id: 2,
      name: 'El padrino',
      href: '/detalle/2',
      imageSrc: padrino,
      imageAlt: "El padrino",
      price: '9.99€',
    },
    {
      id: 3,
      name: 'El cuervo',
      href: '/detalle/3',
      imageSrc: cuervo,
      imageAlt: "El cuervo",
      price: '9.99€',
    },
    {
      id: 4,
      name: 'El padrino',
      href: '/detalle/4',
      imageSrc: padrino,
      imageAlt: "El padrino",
      price: '9.99€',
    },
    {
      id: 5,
      name: 'El cuervo',
      href: '/detalle/5',
      imageSrc: cuervo,
      imageAlt: "El cuervo",
      price: '9.99€',
    },
    {
      id: 6,
      name: 'El padrino',
      href: '/detalle/6',
      imageSrc: padrino,
      imageAlt: "El padrino",
      price: '9.99€',
    },
  ]
  
  export default function FilmsGallery() {
    return (
      <div className="bg-white">
        <div className="max-w-2xl mx-auto py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
          <div className="mt-6 grid grid-cols-1 gap-y-10 gap-x-6 sm:grid-cols-2 lg:grid-cols-5 xl:gap-x-8">
            {products.map((product) => (
              <div key={product.id} className="group relative">
                <div className="w-full min-h-80 bg-gray-200 rounded-md overflow-hidden group-hover:opacity-75 aspect-w-11 aspect-h-16">
                  <img
                    src={product.imageSrc}
                    alt={product.imageAlt}
                    className="object-center object-cover"
                  />
                </div>
                <div className="mt-4 flex justify-between">
                  <div>
                    <h3 className="text-sm text-gray-700">
                      <Link to={product.href}>
                        <span aria-hidden="true" className="absolute inset-0" />
                        {product.name}
                      </Link>
                    </h3>
                  </div>
                  <p className="text-sm font-medium text-gray-900">{product.price}</p>
                </div>
              </div>
            ))}
          </div>
        </div>
        <Pagination/>
      </div>
    )
  }
  