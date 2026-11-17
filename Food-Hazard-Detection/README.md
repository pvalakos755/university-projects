# Food Hazard Detection — NLP Kaggle Competition

An academic team project for the [Food Hazard Detection Kaggle competition](https://www.kaggle.com/competitions/nlp-053-cse-uoi-2026-food-hazard-detection). The goal was to classify food recall reports into **hazard categories** and **product categories** using Natural Language Processing (NLP).

## What we implemented

- Prepared text features from recall titles, descriptions, and metadata.
- Used **TF-IDF** with word and character n-grams.
- Compared several classifiers, including **Complement Naive Bayes**, **SGD Classifier**, and **Linear SVC**, alongside a baseline and a hazard-aware approach.
- Evaluated the models using the **ST1 metric** and generated a Kaggle submission with the best-performing validation model.

## Technologies

Python, Jupyter Notebook, pandas, NumPy, scikit-learn, Matplotlib.

*Team project. Competition datasets are not included in this repository.*
