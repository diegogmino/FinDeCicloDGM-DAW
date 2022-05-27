import Grid from '@mui/material/Grid';

import contacto from '../../img/account.png';

export default function AccountImage() {
    return(
        <div>
            <Grid container component="main" sx={{ height: '500px', mt: 4, }} justifyContent="center">
                <Grid
                    item
                    xs={12}
                    sm={10}
                    md={8}
                    sx={{
                        backgroundImage: `url(${contacto})`,
                        backgroundRepeat: 'no-repeat',
                        backgroundColor: (t) =>
                        t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
                        backgroundSize: 'cover',
                        backgroundPosition: 'center',
                    }}
                />
                
            </Grid>
        </div>
    );
}