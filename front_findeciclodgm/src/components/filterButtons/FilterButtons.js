import * as React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import ButtonBase from '@mui/material/ButtonBase';
import Typography from '@mui/material/Typography';

const images = [
  {
    url: 'https://cdn.pixabay.com/photo/2018/01/23/23/34/nature-3102762_960_720.jpg',
    title: 'Drama',
    width: '40%',
  },
  {
    url: 'https://cdn.pixabay.com/photo/2017/10/17/19/11/fantasy-2861815_960_720.jpg',
    title: 'Ciencia ficción',
    width: '30%',
  },
  {
    url: 'https://cdn.pixabay.com/photo/2019/10/09/13/10/halloween-4537430_960_720.jpg',
    title: 'Terror',
    width: '30%',
  },
  {
    url: 'https://cdn.pixabay.com/photo/2018/06/26/13/04/rose-3499375_960_720.jpg',
    title: 'Romance',
    width: '30%',
  },
  {
    url: 'https://media.gq.com.mx/photos/5f44373eef6115112e88bbee/4:3/w_2248,h_1686,c_limit/animes%20gore%20another.jpg',
    title: 'Anime',
    width: '30%',
  },
  {
    url: 'https://cdn.onebauer.media/one/empire-images/features/5857b3431eed4b23059f64e7/commando4.jpg?format=jpg&quality=80&width=960&height=540&ratio=16-9&resize=aspectfill',
    title: 'Acción',
    width: '40%',
  },
];

const ImageButton = styled(ButtonBase)(({ theme }) => ({
  position: 'relative',
  height: 200,
  [theme.breakpoints.down('sm')]: {
    width: '100% !important', // Overrides inline-style
    height: 100,
  },
  '&:hover, &.Mui-focusVisible': {
    zIndex: 1,
    '& .MuiImageBackdrop-root': {
      opacity: 0.15,
    },
    '& .MuiImageMarked-root': {
      opacity: 0,
    },
    '& .MuiTypography-root': {
      border: '4px solid currentColor',
    },
  },
}));

const ImageSrc = styled('span')({
  position: 'absolute',
  left: 0,
  right: 0,
  top: 0,
  bottom: 0,
  backgroundSize: 'cover',
  backgroundPosition: 'center 40%',
});

const Image = styled('span')(({ theme }) => ({
  position: 'absolute',
  left: 0,
  right: 0,
  top: 0,
  bottom: 0,
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
  color: theme.palette.common.white,
}));

const ImageBackdrop = styled('span')(({ theme }) => ({
  position: 'absolute',
  left: 0,
  right: 0,
  top: 0,
  bottom: 0,
  backgroundColor: theme.palette.common.black,
  opacity: 0.4,
  transition: theme.transitions.create('opacity'),
}));

const ImageMarked = styled('span')(({ theme }) => ({
  height: 3,
  width: 18,
  backgroundColor: theme.palette.common.white,
  position: 'absolute',
  bottom: -2,
  left: 'calc(50% - 9px)',
  transition: theme.transitions.create('opacity'),
}));

export default function FilterButtons() {
  return (
    <Box sx={{ display: 'flex', flexWrap: 'wrap', minWidth: 300, width: '100%' }}>
      {images.map((image) => (
        <ImageButton
          focusRipple
          key={image.title}
          style={{
            width: image.width,
          }}
        >
          <ImageSrc style={{ backgroundImage: `url(${image.url})` }} />
          <ImageBackdrop className="MuiImageBackdrop-root" />
          <Image>
            <Typography
              component="span"
              variant="subtitle1"
              color="inherit"
              sx={{
                position: 'relative',
                p: 4,
                pt: 2,
                pb: (theme) => `calc(${theme.spacing(1)} + 6px)`,
              }}
            >
              {image.title}
              <ImageMarked className="MuiImageMarked-root" />
            </Typography>
          </Image>
        </ImageButton>
      ))}
    </Box>
  );
}
