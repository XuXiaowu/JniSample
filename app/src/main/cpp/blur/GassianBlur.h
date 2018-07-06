//
// Created by 徐晓武 on 2018/4/28.
//

#ifndef JNISAMPLE_GASSIANBLUR_H
#define JNISAMPLE_GASSIANBLUR_H

extern "C" {

void gaussBlur1(int *pix, int w, int h, int radius);
void gaussBlur2(int *pix, int w, int h, int r);

}


#endif //JNISAMPLE_GASSIANBLUR_H
