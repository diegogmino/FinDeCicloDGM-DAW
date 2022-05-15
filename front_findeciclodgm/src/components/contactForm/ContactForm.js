import Grid from '@mui/material/Grid';

export default function ContactForm() {
    return (
        <div>

            <div>
                <div className="text-center mb-6 mt-6">
                     <p className="text-3xl leading-8 font-extrabold tracking-tight text-gray-900 sm:text-4xl">
                        Queremos saber tu opinión
                    </p>
                </div>
            </div>

            <Grid container component="main" sx={{ mb: 3}} justifyContent="center">
                <Grid
                    item
                    xs={12}
                    sm={10}
                    md={7}
                >
                    <form action="#" method="POST">
                        <div className="shadow overflow-hidden sm:rounded">
                            <div className="px-4 py-5 bg-white sm:p-6">
                                <div className="grid grid-cols-6 gap-6">
                                    <div className="col-span-6 sm:col-span-3">
                                        <label htmlFor="first-name" className="block text-sm font-medium text-gray-700">
                                        Nombre
                                        </label>
                                        <input
                                        type="text"
                                        name="nombre"
                                        id="nombre"
                                        autoComplete="given-name"
                                        className="mt-1 focus:ring-principal focus:border-principal block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                                        />
                                    </div>
                                    
                                    <div className="col-span-6 sm:col-span-3">
                                        <label htmlFor="last-name" className="block text-sm font-medium text-gray-700">
                                        Apellido/s
                                        </label>
                                        <input
                                        type="text"
                                        name="apellido"
                                        id="apellido"
                                        autoComplete="family-name"
                                        className="mt-1 focus:ring-principal focus:border-principal block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                                        />
                                    </div>
            
                                    <div className="col-span-6 sm:col-span-4">
                                        <label htmlFor="email-address" className="block text-sm font-medium text-gray-700">
                                        Correo electrónico
                                        </label>
                                        <input
                                        type="text"
                                        name="email"
                                        id="email"
                                        autoComplete="email"
                                        className="mt-1 focus:ring-principal focus:border-principal block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                                        />
                                    </div>
            
                                    <div className="col-span-6 sm:col-span-3">
                                        <label htmlFor="country" className="block text-sm font-medium text-gray-700">
                                        País
                                        </label>
                                        <select
                                        id="pais"
                                        name="pais"
                                        autoComplete="country-name"
                                        className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-principal focus:border-principal sm:text-sm"
                                        >
                                        <option>España</option>
                                        <option>Francia</option>
                                        <option>Portugal</option>
                                        </select>
                                    </div>

                                    <div className="col-span-6">
                                    <label htmlFor="about" className="block text-sm font-medium text-gray-700">
                                        Mensaje
                                    </label>
                                    <div className="mt-1">
                                        <textarea
                                        id="mensaje"
                                        name="mensaje"
                                        rows={3}
                                        className="shadow-sm focus:ring-principal focus:border-principal mt-1 block w-full sm:text-sm border border-gray-300 rounded-md"
                                        placeholder="Escríbenos algo bonito :)"
                                        defaultValue={''}
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                            <div className="px-4 py-3 bg-gray-50 text-right sm:px-6">
                                <button
                                type="submit"
                                className="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-principal hover:bg-gris-oscuro focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                                >
                                Enviar
                                </button>
                            </div>
                        </div>
                    </form>
                </Grid>
            </Grid>
        </div>
    )
  }
  